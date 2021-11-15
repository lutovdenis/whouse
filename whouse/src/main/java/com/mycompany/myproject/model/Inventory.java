package com.mycompany.myproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Entity
public class Inventory {

    public Inventory(){

    }

    public Inventory(long art_id, String name, int stock){
        this.art_id = art_id;
        this.name = name;
        this.stock = stock;
    }

    public Inventory(ArrayList<String> values){
        this.art_id = Long.parseLong(values.get(0));
        this.name = values.get(1);
        this.stock = Integer.parseInt(values.get(2));
    }

    @Id
    private long art_id;
    private String name;
    private int stock;

    public void setArt_id(long art_id) {
        this.art_id = art_id;
    }
    public long getArt_id() { return art_id; }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public int getStock() {
        return stock;
    }



}
