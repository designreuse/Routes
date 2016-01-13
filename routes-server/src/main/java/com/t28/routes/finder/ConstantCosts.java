package com.t28.routes.finder;

import jsprit.core.problem.Location;
import jsprit.core.problem.cost.AbstractForwardVehicleRoutingTransportCosts;
import jsprit.core.problem.driver.Driver;
import jsprit.core.problem.vehicle.Vehicle;

public class ConstantCosts extends AbstractForwardVehicleRoutingTransportCosts{
    private static final double TRANSPORT_TIME = 10.0d;
    private static final double TRANSPORT_COST = 150.0d;

    @Override
    public double getTransportTime(Location from, Location to, double departureTime, Driver driver, Vehicle vehicle) {
        return TRANSPORT_TIME;
    }

    @Override
    public double getTransportCost(Location from, Location to, double departureTime, Driver driver, Vehicle vehicle) {
        return TRANSPORT_COST;
    }
}
