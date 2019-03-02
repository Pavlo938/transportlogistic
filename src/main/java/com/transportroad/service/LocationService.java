package com.transportroad.service;

import com.transportroad.model.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<Location> getLocations(); //to fetch all the locations from the database

    Optional<Location> findById(Long id);

    Location save (Location location);

    void deleteById(long id);

    List<Location> getLocationsByIdIn(List<Long> ids);
}
