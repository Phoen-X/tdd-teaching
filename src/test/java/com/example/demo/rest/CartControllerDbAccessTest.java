package com.example.demo.rest;

import com.example.demo.DemoApplication;
import com.example.demo.repo.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:h2-application.properties")
public class CartControllerDbAccessTest {
    @Autowired
    private MockMvc web;

    @Autowired
    private CartRepository repo;

    @Autowired
    private JdbcTemplate jdbc;

    @AfterEach
    void tearDown() {
        jdbc.execute("delete from cart");
    }

    @Test
    void cartIsSearchedInDatabase() throws Exception {
        //execute sql script
        jdbc.execute("insert into cart(id, name) values ('13' , 'Tea')");

        web.perform(get("/cart/13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value("13"));
    }
}
