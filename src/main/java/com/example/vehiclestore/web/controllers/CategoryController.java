
package com.example.vehiclestore.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vehiclestore.DAO.entities.Category;
import com.example.vehiclestore.business.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> getCategories() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable("id") long id) {
        return new ResponseEntity<>(categoryService.getCategory(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        Category existingCategory = categoryService.getCategoryByName(category.getName());

        if (existingCategory != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category " + category.getName() + " already exists");
        } else {
            categoryService.addCategory(new Category(null, category.getName(), category.getDescription(), category.getImage()));
            return ResponseEntity.status(HttpStatus.CREATED).body(category);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Long id) {
        for (Category category : categoryService.getAllCategory()) {
            if (category.getId().equals(id)) {
                categoryService.deleteCategory(id);
            }
            return new ResponseEntity<>("Category is deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed: Category not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

}
