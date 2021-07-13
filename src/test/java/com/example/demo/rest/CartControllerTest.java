package com.example.demo.rest;

import com.example.demo.repo.CartRepository;
import com.example.demo.repo.entity.CartEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
If you test ONLY web integration as we do here, it's better to use annotation WebMvcTest. It will not create full application context (with db connection etc.),
but rather only web part (controllers, advices, web security) and additionally it already configures MockMvc.

If you want to test full route  (from http request through controller to database, without mocks) you should still use @SpringBootTest annotation
 */
@WebMvcTest
class CartControllerTest {

    @Autowired
    private MockMvc web;

    @MockBean
    private CartRepository repo;

    @Test
    void cartCanBeFoundById() throws Exception {
        when(repo.findCart("13")).thenReturn(Optional.of(new CartEntity("13", "Some name")));

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