package com.example.demo.rest;

import com.example.demo.cart.Cart;
import com.example.demo.repo.entity.CartEntity;

public class CartResponse {
    private String id;

    public CartResponse(String id) {
        this.id = id;
    }

    public static CartResponse fromEntity(CartEntity cart) {
        return new CartResponse(cart.getId());
    }

    public String getId() {
        return id;
    }
}
