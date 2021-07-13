package com.example.demo.rest;

import com.example.demo.repo.CartRepository;
import com.example.demo.repo.entity.CartEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:h2-application.properties")
/* This annotation can be used on class (will execute before every test (!!!) in that class), ot a method (will execute before that test)
There is class ScriptUtils in spring, which allows you to manually run a script file against some db connection (without injecting jdbc template).
I don't know when you would want to use that though :)
 */
@Sql(scripts = "classpath:sql/cart.sql")
public class CartRepoTest {

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
        jdbc.execute("insert into cart(`id`, `name`) values ('13' , 'Tea')");

        Optional<CartEntity> cart = repo.findCart("13");

        assertThat(cart).contains(new CartEntity("13", "Tea"));
    }
}
