package com.example.demo.cart;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    public static final Product BREAD = new Product("Bread", 10);
    public static final Product TEA = new Product("Tea", 25);

    @Test
    void emptyCartCanBeCreated() {
        Cart cart = new Cart();
        assertThat(cart).isNotNull();
    }

    @Test
    void itemCanBeAddedToCart() {
        Cart cart = new Cart();
        cart.add(BREAD);

        assertThat(cart.getItems()).containsExactly(BREAD);
    }

    @Test
    void multipleItemsCanBeAdded() {
        Cart cart = new Cart();

        cart.add(BREAD, TEA);

        assertThat(cart.getItems()).containsExactlyInAnyOrder(TEA, BREAD);
    }

    @Test
    void totalPriceIsCounted() {
        Cart cart = new Cart();
        cart.add(BREAD, TEA);
        assertThat(cart.getTotal()).isEqualTo(35);
    }
}