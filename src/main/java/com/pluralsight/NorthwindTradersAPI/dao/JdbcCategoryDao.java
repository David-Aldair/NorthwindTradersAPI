package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcCategoryDao implements ICategoryDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Category> getAll(){

        //creating an empty list to hold the product object we will retrieve
        List<Category> categories = new ArrayList<>();

        //this is the sql select statement we will run
        String sql = "SELECT CategoryID, CategoryName";

        //try with resources block
        //this ensure that the connection, statement, and result set are closed automatically after we are done
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            //looping through each row in the result set
            while(resultSet.next()){

                //create new category object
                Category category = new Category();

                //set the CategoryId from the categoryID column
                category.setCategoryId(resultSet.getInt("CategoryID"));

                //set the category categoryName from the categoryName column
                category.setCategoryName(resultSet.getString("CategoryName"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public Category getById(int id){

        //create an empty category
        Category category = new Category();

        //sql select statement
        String sql = "SELECT CategoryID, CategoryName FROM categories WHERE CategoryID = ?";

        //try with resources block
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

        ){
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery(sql);

            //looping through each row
            if (resultSet.next()){

                //set the category's id from the categoryId column
                category.setCategoryId(resultSet.getInt("CategoryID"));

                //set the category categoryName from the categoryName column
                category.setCategoryName(resultSet.getString("CategoryName"));
            }else{
                //return the 404 if the query above returned 0 results
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }
}
