package com.example.demo.cart;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public void add(Product... products) {
        this.products.addAll(asList(products));
    }

    public List<Product> getItems() {
        return products;
    }

    public int getTotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(0, Integer::sum);
    }

}
