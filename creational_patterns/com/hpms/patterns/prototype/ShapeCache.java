package com.hpms.patterns.prototype;

import java.util.HashMap;
import java.util.Map;

public class ShapeCache {
    private final Map<String, Shape> shapeMap = new HashMap<>();

    public void loadCache() {
        Circle circle = new Circle(5.0);
        circle.setId("circle1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle(4.0, 6.0);
        rectangle.setId("rectangle1");
        shapeMap.put(rectangle.getId(), rectangle);
    }

    public Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        if (cachedShape == null) {
            return null;
        }
        return cachedShape.clone();
    }
}