package com.hpms.patterns.simplefactory;

public class Car implements Vehicle {
    @Override
    public String getType() {
        return "Car";
    }
}