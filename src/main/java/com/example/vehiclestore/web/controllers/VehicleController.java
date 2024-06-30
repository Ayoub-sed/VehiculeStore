package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclestore.DAO.entities.Category;
import com.example.vehiclestore.DAO.entities.Vehicle;
import com.example.vehiclestore.business.services.CategoryService;
import com.example.vehiclestore.business.services.VehicleService;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id);
    }

    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        String categoryName = vehicle.getCategory().getName();
        Category category = categoryService.getCategoryByName(categoryName);

        if (category == null) {
            throw new EntityNotFoundException("Category not found");
        } else {
            vehicle.setCategory(category);
            return vehicleService.createVehicle(vehicle);
        }
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(vehicleService.getClass().getName() + " is deleted", HttpStatus.OK);
    }

}

/*
 * @RestController
 * public class ProductController {
 * 
 * private static List<Product> products = new ArrayList<Product>();
 * private static Long idCount = 0L;
 * 
 * static {
 * 
 * products.add(new Product(++idCount, "SS-S9", "Samsung Galaxy S9", 500D, 50,
 * "samsung-s9.png"));
 * products.add(new Product(++idCount, "NK-5P", "Nokia 5.1 Plus", 60D, 60,
 * null));
 * products.add(new Product(++idCount, "IP-7", "Iphone 7", 600D, 30,
 * "iphone-7.png"));
 * 
 * }
 * public static String uploadDir = System.getProperty("user.dir") +
 * "/src/main/resources/static/images";
 * 
 * @PostMapping("/products")
 * public ResponseEntity<Object> createProduct(@RequestBody() ProductForm
 * productForm) {
 * Product product = new Product(++idCount, productForm.getCode(),
 * productForm.getName(), productForm.getPrice(), productForm.getQuantity(),
 * productForm.getImage());
 * products.add(product);
 * return new ResponseEntity<>(product,HttpStatus.CREATED);
 * }
 * 
 * 
 * @GetMapping("/products")
 * public ResponseEntity<Object> getProduct(){
 * return new ResponseEntity<>(products,HttpStatus.OK);
 * }
 * 
 * @GetMapping("/products/{id}")
 * public ResponseEntity<Object> getProductById(@PathVariable("id") Long id) {
 * for(Product product: products){
 * if(product.getId().equals(id)){
 * return new ResponseEntity<>(product,HttpStatus.OK);
 * }
 * }
 * return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
 * 
 * }
 * 
 * @GetMapping("/products/code/{code}")
 * public ResponseEntity<Object> getProductByCode(@PathVariable() String code) {
 * for(Product product: products){
 * if(product.getCode().equals(code)){
 * return new ResponseEntity<>(product,HttpStatus.OK);
 * }
 * }
 * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 * 
 * }
 * 
 * @GetMapping("/products/sorted")
 * public ResponseEntity<Object> getProductSortedByName() {
 * List<Product> sortedProducts =
 * products.stream().sorted(Comparator.comparing(Product ::
 * getName)).collect(Collectors.toList());
 * return new ResponseEntity<>(sortedProducts,HttpStatus.OK);
 * }
 * 
 * @GetMapping("/products/sortedByPrice")
 * public ResponseEntity<Object> getProductSortedByPrice() {
 * List<Product> sortedProducts =
 * products.stream().sorted(Comparator.comparing(Product ::
 * getPrice)).collect(Collectors.toList());
 * return new ResponseEntity<>(sortedProducts,HttpStatus.OK);
 * }
 * 
 * @PutMapping("/products/{id}")
 * public ResponseEntity<Object> updateProduct(@PathVariable Long id
 * , @RequestBody ProductForm productForm){
 * for(Product product : products){
 * if(product.getId().equals(id)){
 * product.setCode(productForm.getCode());
 * product.setName(productForm.getName());
 * product.setPrice(productForm.getPrice());
 * product.setQuantity(productForm.getQuantity());
 * product.setImage(productForm.getImage());
 * return new ResponseEntity<>(product,HttpStatus.OK);
 * }
 * }
 * return new ResponseEntity<>("Failed",HttpStatus.NOT_FOUND);
 * }
 * 
 * @DeleteMapping("/products/{id}")
 * public ResponseEntity<Object> deleteProduct(@PathVariable Long id ) {
 * for(Product product : products){
 * if(product.getId().equals(id)){
 * products.remove(product);
 * return new ResponseEntity<>(product.getName()+" is deleted",HttpStatus.OK);
 * }
 * }
 * 
 * return new ResponseEntity<>("product NOT FOUND",HttpStatus.OK);
 * }
 * 
 * }
 */