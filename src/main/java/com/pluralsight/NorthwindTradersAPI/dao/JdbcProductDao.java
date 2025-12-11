package com.pluralsight.NorthwindTradersAPI.dao;

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
public class JdbcProductDao implements IProductDao {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Product> getAll(){

        //creating an empty list to hold the product objects we will retrieve
        List<Product> products = new ArrayList<>();

        //this is the SQL select statement we will run
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products";

        //try with resources block
        //this ensures that the connection, statement, and result set are closed automatically after we are done
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            //looping through each row in the result set
            while(resultSet.next()){

                //create new product object
                Product product = new Product();

                //set the productId from the ProductID column
                product.setProductId(resultSet.getInt("ProductID"));

                //set the productName from the ProductName column
                product.setProductName(resultSet.getString("ProductName"));

                //set the CategoryID from the CategoryID column
                product.setCategoryId(resultSet.getInt("CategoryID"));

                //set the unitPrice from the UnitPrice column
                product.setUnitPrice(resultSet.getDouble("UnitPrice"));

                //add the product to the list
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getById(int id){

        //create an empty product object
        Product product = new Product();

        //sql select statement
        String sql = "SELECT ProductID, ProductName, CategoryID, UnitPrice FROM Products WHERE ProductID = ?";

        //try with resources block
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            //set the parameter
            statement.setInt(1, id);

            //execute the query (no SQL string needed here!)
            ResultSet resultSet = statement.executeQuery();

            //check if the product exists
            if(resultSet.next()){

                //set the productId from the ProductID column
                product.setProductId(resultSet.getInt("ProductID"));

                //set the productName from the ProductName column
                product.setProductName(resultSet.getString("ProductName"));

                //set the categoryId from the CategoryID column
                product.setCategoryId(resultSet.getInt("CategoryID"));

                //set the unitPrice from the UnitPrice column
                product.setUnitPrice(resultSet.getDouble("UnitPrice"));

            } else {
                //return 404 if nothing is found
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return product;
    }
}

