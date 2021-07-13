package com.example.demo.repo;

import com.example.demo.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JdbcCartRepository implements CartRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcCartRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Cart> findCart(String id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from cart where id = " + id, Cart.class));
    }
}
