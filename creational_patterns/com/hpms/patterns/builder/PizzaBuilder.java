package com.hpms.patterns.builder;

import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder {
    private final String size;
    private boolean cheese;
    private boolean extraSauce;
    private final List<String> toppings;

    public PizzaBuilder(String size) {
        if (size == null || size.isBlank()) {
            throw new IllegalArgumentException("Pizza size is required.");
        }
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    public PizzaBuilder addCheese() {
        this.cheese = true;
        return this;
    }

    public PizzaBuilder addExtraSauce() {
        this.extraSauce = true;
        return this;
    }

    public PizzaBuilder addTopping(String topping) {
        if (topping == null || topping.isBlank()) {
            throw new IllegalArgumentException("Topping cannot be blank.");
        }
        toppings.add(topping.trim());
        return this;
    }

    public Pizza build() {
        if (toppings.size() > 10) {
            throw new IllegalStateException("Pizza cannot have more than 10 toppings.");
        }
        return new Pizza(size, cheese, extraSauce, toppings);
    }
}