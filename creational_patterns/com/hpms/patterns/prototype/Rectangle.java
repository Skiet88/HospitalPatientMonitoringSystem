package com.hpms.patterns.prototype;

public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle() {
        this.type = "Rectangle";
    }

    public Rectangle(double width, double height) {
        this();
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}