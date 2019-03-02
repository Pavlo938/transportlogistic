package com.transportroad.service.impl;

import com.transportroad.model.domain.Location;
import com.transportroad.repository.LocationRepository;
import com.transportroad.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LocationServiceImpl implements LocationService {


    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getLocations() {

        return  locationRepository.findAll() ;
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location save (Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteById(long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<Location> getLocationsByIdIn(List<Long> ids) {
        return locationRepository.getLocationsByIdIn(ids);
    }


}
