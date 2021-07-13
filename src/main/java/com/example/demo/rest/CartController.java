package com.example.demo.rest;

import com.example.demo.repo.CartRepository;
import com.example.demo.repo.entity.CartEntity;
import com.example.demo.rest.exception.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {
    private final CartRepository repository;

    @Autowired
    public CartController(CartRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public CartResponse findCart(@PathVariable("id") String id) {
        Optional<CartEntity> repo = repository.findCart(id);
        return repo.map(r -> CartResponse.fromEntity(r))
                .orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " is not found"));
    }
}
