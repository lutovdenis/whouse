package com.mycompany.myproject.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContainArticles() {
        return containArticles;
    }

    public void setContainArticles(String containArticles) {
        this.containArticles = containArticles;
    }

    private String containArticles;

    public Product(){

    }
    public Product(String name, String containArticles){
        this.name = name;
        this.containArticles = containArticles;
    }
}
