package com.hpms.patterns;

import com.hpms.patterns.builder.Pizza;
import com.hpms.patterns.builder.PizzaBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuilderTest {

    @Test
    void buildsPizzaWithOptionalIngredients() {
        Pizza pizza = new PizzaBuilder("Large")
                .addCheese()
                .addExtraSauce()
                .addTopping("Mushroom")
                .addTopping("Olives")
                .build();

        assertEquals("Large", pizza.getSize());
        assertTrue(pizza.hasCheese());
        assertTrue(pizza.hasExtraSauce());
        assertEquals(2, pizza.getToppings().size());
    }

    @Test
    void rejectsBlankTopping() {
        PizzaBuilder builder = new PizzaBuilder("Medium");
        assertThrows(IllegalArgumentException.class, () -> builder.addTopping(" "));
    }
}