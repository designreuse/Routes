package com.t28.routes.finder;

import com.t28.routes.entity.itinerary.Constraint;
import com.t28.routes.entity.itinerary.Entry;
import com.t28.routes.entity.itinerary.Itinerary;
import com.t28.routes.entity.place.Place;
import com.t28.routes.util.Pair;
import jsprit.core.algorithm.VehicleRoutingAlgorithm;
import jsprit.core.algorithm.box.Jsprit;
import jsprit.core.problem.AbstractVehicle;
import jsprit.core.problem.Location;
import jsprit.core.problem.VehicleRoutingProblem;
import jsprit.core.problem.job.Service;
import jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import jsprit.core.problem.solution.route.VehicleRoute;
import jsprit.core.problem.solution.route.activity.TourActivity;
import jsprit.core.problem.vehicle.VehicleImpl;
import jsprit.core.problem.vehicle.VehicleType;
import jsprit.core.problem.vehicle.VehicleTypeImpl;
import jsprit.core.util.Coordinate;
import jsprit.core.util.Solutions;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class JspritFinder implements Finder {
    private final Deque<Pair<Place, Constraint>> pairs;

    public JspritFinder() {
        pairs = new LinkedList<Pair<Place, Constraint>>();
    }

    @Override
    public Finder add(Place place) {
        add(place, Constraint.EMPTY);
        return this;
    }

    @Override
    public Finder add(Place place, Constraint constraint) {
        pairs.add(Pair.of(place, constraint));
        return this;
    }

    @Override
    public Itinerary find() throws NotFoundException {
        if (pairs.size() < 2) {
            throw new NotFoundException("pairs.size() < 2");
        }

        final VehicleRoutingProblem problem = createProblem();
        final VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);
        algorithm.setMaxIterations(16);
        final VehicleRoutingProblemSolution solution = Solutions.bestOf(algorithm.searchSolutions());
        final List<VehicleRoute> routes = new ArrayList<VehicleRoute>(solution.getRoutes());
        if (routes.size() == 0) {
            throw new NotFoundException("routes.size() == 0");
        }

        final VehicleRoute route = routes.get(0);
        final Itinerary itinerary = new Itinerary();
        itinerary.add(toEntry(route.getStart()));
        for (TourActivity activity : route.getTourActivities().getActivities()) {
            itinerary.add(toEntry(activity));
        }
        itinerary.add(toEntry(route.getEnd()));
        return itinerary;
    }

    public Entry toEntry(TourActivity activity) {
        final Location location = activity.getLocation();
        return new Entry(location.getId(), findPlaceById(location.getId()), (long) activity.getArrTime(), (long) activity.getEndTime());
    }

    public Place findPlaceById(String id) {
        for (Pair<Place, Constraint> pair : pairs) {
            final Place place = pair.getValue1();
            if (id.equals(place.getObjectId())) {
                return place;
            }
        }
        throw new RuntimeException("Place is not found by " + id);
    }

    public VehicleType createVehicleType() {
        return VehicleTypeImpl.Builder.newInstance("routes-vehicle-type").build();
    }

    public AbstractVehicle createVehicle(Pair<Place, Constraint> origin, Pair<Place, Constraint> destination) {
        // TODO: Need to configure constraint
        final VehicleImpl.Builder builder = VehicleImpl.Builder.newInstance("routes-vehicle");
        builder.setType(createVehicleType())
                .setReturnToDepot(true)
                .setStartLocation(createLocation(origin.getValue1()))
                .setEndLocation(createLocation(destination.getValue1()));
        return builder.build();
    }

    public Location createLocation(Place place) {
        return Location.Builder.newInstance()
                .setIndex(0)
                .setId(place.getObjectId())
                .setCoordinate(Coordinate.newInstance(
                        place.getLocation().getCoordinate().getLat(),
                        place.getLocation().getCoordinate().getLon())
                )
                .build();
    }

    public Set<Service> createJobs(Deque<Pair<Place, Constraint>> places) {
        final Set<Service> jobs = new HashSet<Service>();
        Pair<Place, Constraint> pair;
        // TODO: Need to rewire code
        while ((pair = places.removeFirst()) != null) {
            final Place place = pair.getValue1();
            // TODO: Need to configure constraint
            final Constraint constraint = pair.getValue2();
            final Service service = Service.Builder.newInstance(place.getObjectId())
                    .setLocation(createLocation(place))
                    .build();
            jobs.add(service);
            if (places.size() == 0) {
                break;
            }
        }
        return jobs;
    }

    public VehicleRoutingProblem createProblem() {
        final Deque<Pair<Place, Constraint>> pairs = new LinkedList<Pair<Place, Constraint>>(this.pairs);
        return VehicleRoutingProblem.Builder.newInstance()
                .addVehicle(createVehicle(pairs.removeFirst(), pairs.removeLast()))
                .addAllJobs(createJobs(pairs))
                .setRoutingCost(new ConstantCosts())
                .build();
    }
}
