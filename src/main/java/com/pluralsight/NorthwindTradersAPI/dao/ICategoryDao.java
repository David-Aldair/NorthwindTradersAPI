package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ICategoryDao {

    //CRUD: create, read, update, delete

    //C(R)UD
    List<Category> getAll();

    Category findById(int id);

}
