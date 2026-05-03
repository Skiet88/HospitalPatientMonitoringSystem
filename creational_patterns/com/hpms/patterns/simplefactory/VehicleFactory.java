package com.hpms.patterns.simplefactory;

public final class VehicleFactory {
    private VehicleFactory() {
    }

    public static Vehicle createVehicle(String type) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Vehicle type is required.");
        }
        return switch (type.trim().toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            case "truck" -> new Truck();
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + type);
        };
    }
}