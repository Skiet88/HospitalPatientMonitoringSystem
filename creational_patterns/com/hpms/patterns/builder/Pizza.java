package com.hpms.patterns.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private final String size;
    private final boolean cheese;
    private final boolean extraSauce;
    private final List<String> toppings;

    Pizza(String size, boolean cheese, boolean extraSauce, List<String> toppings) {
        this.size = size;
        this.cheese = cheese;
        this.extraSauce = extraSauce;
        this.toppings = new ArrayList<>(toppings);
    }

    public String getSize() {
        return size;
    }

    public boolean hasCheese() {
        return cheese;
    }

    public boolean hasExtraSauce() {
        return extraSauce;
    }

    public List<String> getToppings() {
        return Collections.unmodifiableList(toppings);
    }
}