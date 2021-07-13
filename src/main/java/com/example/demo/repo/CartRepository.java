package com.example.demo.repo;

import com.example.demo.cart.Cart;

import java.util.Optional;

public interface CartRepository {

    Optional<Cart> findCart(String id);
}
