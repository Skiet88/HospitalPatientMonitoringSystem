package com.hpms.patterns.prototype;

public class Circle extends Shape {
    private double radius;

    public Circle() {
        this.type = "Circle";
    }

    public Circle(double radius) {
        this();
        this.radius = radius;
    }

    @Override
    public void draw() {
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}