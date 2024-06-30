package com.example.vehiclestore.business.servicesImp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vehiclestore.DAO.entities.Product;
import com.example.vehiclestore.DAO.repository.ProductRepository;
import com.example.vehiclestore.business.services.ProductService;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product product1 = productRepository.findById(id).orElseThrow();

        product1.setCode(product.getCode());
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setQuantity(product.getQuantity());
        product1.setImage(product.getImage());
        return productRepository.save(product1);
    }

    @Override
    public Product getProduct(long idProduct) {
        return productRepository.findById(idProduct).get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long idProduct) {
        productRepository.deleteById(idProduct);
    }

}
