package com.example.demo.cart;

public class Product {
    private final String name;
    private final int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return adjust(name);
    }

    private String adjust(String name) {
        return name.startsWith("0") ? name : "0" + name;
    }
}
