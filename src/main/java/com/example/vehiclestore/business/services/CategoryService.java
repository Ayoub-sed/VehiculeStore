package com.example.vehiclestore.business.services;

import java.util.List;

import com.example.vehiclestore.DAO.entities.Category;

public interface CategoryService {

    public Category addCategory(Category category);

    public Category updateCategory(Long id, Category category);

    public Category getCategory(Long id);

    public void deleteCategory(Long id);

    public List<Category> getAllCategory();

    public Category getCategoryById(Long id);

    public Category getCategoryByName(String name);

}
