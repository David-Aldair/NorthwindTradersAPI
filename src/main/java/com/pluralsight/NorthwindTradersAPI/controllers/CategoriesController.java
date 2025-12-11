package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ICategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    @Qualifier("jdbcCategoryDao")
    ICategoryDao categoryDao;

    @GetMapping("/products")
    public List<Category> getAllProducts(){
        return categoryDao.getAll();
    }

    @GetMapping("/products/{id}")
    public Category getProductById(@PathVariable int id){
        return categoryDao.findById(id);
    }
}
