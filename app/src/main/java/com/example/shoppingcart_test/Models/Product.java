package com.example.shoppingcart_test.Models;

import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

    private String uid;

    private int id;

    private String name;

    private int qty;

    private double price;

    private String description;

    private String img;

    private String category;

    public Product(int id, String name, int qty, double price, String img,String category) {
        this.uid = UUID.randomUUID().toString();
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.img = img;
        this.category = category;
    }

    public Product() {
    }

    public String getUid() {
        return uid;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImg() {
        return img;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uid='" + uid + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
