package com.transportroad.service.impl;

import com.transportroad.exception.LocationNotFoundException;
import com.transportroad.model.domain.Location;
import com.transportroad.repository.LocationRepository;
import com.transportroad.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Location> getLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Location> findById(Long id) {

        return locationRepository.findById(id);
    }

    @Override
    public Location save(Location location) {

        return locationRepository.save(location);
    }

    @Override
    public void deleteById(long id) {

        locationRepository.deleteById(id);
    }

    @Override
    public Location updateLocation(Location location) {

        Optional.ofNullable(location.getId())
                .orElseThrow(() -> new LocationNotFoundException("Cannot update location with this id"));

        return locationRepository.save(location);
    }

}


