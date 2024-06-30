package com.example.vehiclestore.business.services;


import java.util.List;

import com.example.vehiclestore.DAO.entities.Product;


public interface ProductService {

    public Product addProduct(Product p);

    public Product updateProduct(long id, Product P);

    public Product getProduct(long idProduct);

    public List<Product> getAllProduct();

    public void deleteProduct(Long idProduct);

}
