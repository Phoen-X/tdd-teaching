package com.example.demo.rest;

import com.example.demo.DemoApplication;
import com.example.demo.cart.Cart;
import com.example.demo.repo.CartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc web;

    @MockBean
    private CartRepository repo;

    @Test
    void cartCanBeFoundById() throws Exception {
        when(repo.findCart("13")).thenReturn(Optional.of(new Cart()));

        web.perform(get("/cart/13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("13"));
    }

    @Test
    void errorIsThrownWhenCartIsNotFound() throws Exception {
        when(repo.findCart("100")).thenReturn(Optional.empty());

        web.perform(get("/cart/100"))
                .andExpect(status().isNotFound())
        .andExpect(jsonPath("reason").value("Cart with id 100 is not found"));
    }
}