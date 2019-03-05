package com.transportroad.service;

import com.transportroad.model.domain.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    List<Location> getLocations();

    Optional<Location> findById(Long id);

    Location save (Location location);

    void deleteById(long id);

    //List<Location> getLocationsByIdIn(List<Long> ids);

     Location updateLocation(Location location);

     // List<Location> locationsValidator(List<Long> list);
}
