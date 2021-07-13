package com.example.demo.repo;

import com.example.demo.repo.entity.CartEntity;

import java.util.Optional;

public interface CartRepository {

    Optional<CartEntity> findCart(String id);
}
