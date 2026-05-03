package com.hpms.patterns;

import com.hpms.patterns.simplefactory.Bike;
import com.hpms.patterns.simplefactory.Car;
import com.hpms.patterns.simplefactory.Truck;
import com.hpms.patterns.simplefactory.Vehicle;
import com.hpms.patterns.simplefactory.VehicleFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SimpleFactoryTest {

    @Test
    void createsExpectedVehicleTypes() {
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle bike = VehicleFactory.createVehicle("bike");
        Vehicle truck = VehicleFactory.createVehicle("truck");

        assertInstanceOf(Car.class, car);
        assertInstanceOf(Bike.class, bike);
        assertInstanceOf(Truck.class, truck);
    }

    @Test
    void throwsForUnsupportedVehicleType() {
        assertThrows(IllegalArgumentException.class, () -> VehicleFactory.createVehicle("plane"));
    }
}