package com.pluralsight.NorthwindTradersAPI.models;

public class Category {

    //properties
    private Integer categoryId;
    private String categoryName;

    //constructor with no parameters
    public Category(){}

    //constructor with parameters
    public Category(String categoryName, Integer categoryId) {
        this.categoryName = categoryName;
        this.categoryId = categoryId;
    }

    //getters and setters
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }



}
