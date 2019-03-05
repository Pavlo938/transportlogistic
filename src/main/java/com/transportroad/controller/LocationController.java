package com.transportroad.controller;

import com.transportroad.exception.LocationNotFoundException;
import com.transportroad.model.domain.Location;
import com.transportroad.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/locations")
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> locations() {
        List<Location> locations = locationService.getLocations();
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> findById(@PathVariable Long id) {

        Location location = locationService.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location is not found " + id));
        return ResponseEntity.ok(location);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {

        Location newLocation = locationService.save(location);
        return ResponseEntity.ok(newLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Location> deleteRoute(@PathVariable Long id) {

        locationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Location> updateLocation(@RequestBody Location location) {

        Location updatedLocation = locationService.updateLocation(location);
        return ResponseEntity.ok(updatedLocation);
    }

}
