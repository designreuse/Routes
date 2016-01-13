package com.t28.routes.finder;

import com.t28.routes.entity.itinerary.Constraint;
import com.t28.routes.entity.itinerary.Itinerary;
import com.t28.routes.entity.place.Place;

public interface Finder {
    Finder add(Place place);

    Finder add(Place place, Constraint constraint);

    Itinerary find() throws NotFoundException;
}
