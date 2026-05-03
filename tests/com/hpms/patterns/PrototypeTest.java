package com.hpms.patterns;

import com.hpms.patterns.prototype.Circle;
import com.hpms.patterns.prototype.Shape;
import com.hpms.patterns.prototype.ShapeCache;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrototypeTest {

    @Test
    void clonesPrototypeFromCache() {
        ShapeCache cache = new ShapeCache();
        cache.loadCache();

        Shape clone = cache.getShape("circle1");
        Shape clone2 = cache.getShape("circle1");

        assertTrue(clone instanceof Circle);
        assertEquals("Circle", clone.getType());
        assertNotSame(clone, clone2);
    }
}