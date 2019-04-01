package com.example.stewa.uniproject;

public class Product {

    private String name;
    private String description;
    private double itemCost;
    private int stock;
    private double weight;
    private String producer;


    public Product(String name, String description, double itemCost, int stock, double weight, String producer){
        this.name = name;
        this.description = description;
        this.itemCost = itemCost;
        this.stock = stock;
        this.weight= weight;
        this.producer = producer;
    }






   //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }





}
