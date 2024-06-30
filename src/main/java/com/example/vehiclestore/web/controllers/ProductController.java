
package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import com.example.vehiclestore.business.services.ProductService;
import com.example.vehiclestore.business.services.CategoryService;
import com.example.vehiclestore.DAO.entities.Product;

@RequestMapping("/products")
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService CategoryService;

    @GetMapping
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") long idp) {
        return new ResponseEntity<>(productService.getProduct(idp), HttpStatus.OK);
    }

    @GetMapping("/category")
    public void getAllCategory() {
        CategoryService.getAllCategory();
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        for (Product product : productService.getAllProduct()) {
            if (product.getId().equals(id)) {
                productService.deleteProduct(id);
            }
            return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed: Product not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long idp, @RequestBody Product product) {
        {
            return productService.updateProduct(idp, product);
        }
    }
}
