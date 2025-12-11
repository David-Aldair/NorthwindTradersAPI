package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.IProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    @Qualifier("jdbcProductDao")
    IProductDao productDao;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productDao.getAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){
        return productDao.findById(id);
    }
}
