package com.hpms.patterns.simplefactory;

public class Bike implements Vehicle {
    @Override
    public String getType() {
        return "Bike";
    }
}