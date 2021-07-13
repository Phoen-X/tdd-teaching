package com.example.demo.rest;

import com.example.demo.cart.Cart;

public class CartResponse {
    private String id;

    public CartResponse(String id) {
        this.id = id;
    }

    public static CartResponse fromEntity(String id, Cart cart) {
        return new CartResponse(id);
    }

    public String getId() {
        return id;
    }
}
