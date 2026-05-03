package com.hpms.patterns.simplefactory;

public class Truck implements Vehicle {
    @Override
    public String getType() {
        return "Truck";
    }
}