package com.t28.routes.finder;

import com.t28.routes.http.HttpException;
import com.t28.routes.http.HttpResponse;
import com.t28.routes.http.google.Google;
import com.t28.routes.http.google.entity.DistanceMatrix;
import com.t28.routes.http.google.entity.Element;
import com.t28.routes.http.google.entity.Elements;
import jsprit.core.problem.Location;
import jsprit.core.problem.cost.AbstractForwardVehicleRoutingTransportCosts;
import jsprit.core.problem.driver.Driver;
import jsprit.core.problem.vehicle.Vehicle;
import jsprit.core.util.Coordinate;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class DistanceMatrixCosts extends AbstractForwardVehicleRoutingTransportCosts{
    private final Google context;
    private final Map<String, Element> cache;

    public DistanceMatrixCosts(Google context) {
        this.context = context;
        this.cache = new HashMap<String, Element>();
    }

    @Override
    public double getTransportTime(Location from, Location to, double departureTime, Driver driver, Vehicle vehicle) {
        final Coordinate coordinate1 = from.getCoordinate();
        final Coordinate coordinate2 = to.getCoordinate();

        final Element cached = get(coordinate1, coordinate2);
        if (cached != null) {
            return cached.getDuration().getValue();
        }

        try {
            final Element element = fetch(coordinate1, coordinate2);
            put(coordinate1, coordinate2, element);
            return element.getDuration().getValue();
        } catch (HttpException e) {
            // TODO: need to output message to a log file
            return Double.MAX_VALUE;
        }
    }

    @Override
    public double getTransportCost(Location from, Location to, double departureTime, Driver driver, Vehicle vehicle) {
        final Coordinate coordinate1 = from.getCoordinate();
        final Coordinate coordinate2 = to.getCoordinate();

        final Element cached = get(coordinate1, coordinate2);
        if (cached != null) {
            return cached.getDistance().getValue();
        }

        try {
            final Element element = fetch(coordinate1, coordinate2);
            put(coordinate1, coordinate2, element);
            return element.getDistance().getValue();
        } catch (HttpException e) {
            // TODO: need to output message to a log file
            return Double.MAX_VALUE;
        }
    }

    private void put(Coordinate coordinate1, Coordinate coordinate2, Element element) {
        final String key = String.valueOf(coordinate1.getX() + coordinate1.getY() + coordinate2.getX() + coordinate2.getY());
        cache.put(key, element);
    }

    private Element get(Coordinate coordinate1, Coordinate coordinate2) {
        final String key = String.valueOf(coordinate1.getX() + coordinate1.getY() + coordinate2.getX() + coordinate2.getY());
        return cache.get(key);
    }

    private Element fetch(Coordinate coordinate1, Coordinate coordinate2) throws HttpException {
        final HttpResponse<DistanceMatrix> response = context.maps().distanceMatrix()
                .origin(coordinate1.getX(), coordinate1.getY())
                .destination(coordinate2.getX(), coordinate2.getY())
                .send();

        if (response.getStatusCode() != Response.Status.OK.getStatusCode()) {
            throw new HttpException("Status code != " + Response.Status.OK.getStatusCode());
        }

        final DistanceMatrix matrix = response.getResult();
        if (!matrix.isSuccess()) {
            throw new HttpException("Received response is not successful:" + matrix.getStatus());
        }

        if (matrix.size() == 0) {
            throw new HttpException("Routes not found");
        }

        final Elements elements = matrix.getRowAt(0);
        if (elements.size() == 0) {
            throw new HttpException("Routes not found");
        }

        final Element element = elements.get(0);
        if (!element.isSuccess()) {
            throw new HttpException("Received element is not successful:" + element.getStatus());
        }
        return element;
    }
}
