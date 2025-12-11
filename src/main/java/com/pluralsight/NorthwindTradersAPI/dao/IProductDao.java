package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface IProductDao {

    //CRUD: create, read, update, delete

    //C(R)UD
    List<Product> getAll();

    Product getById(int id);

}
