package com.transportroad.controller;

import com.transportroad.model.domain.Location;
import com.transportroad.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

// TODO: 02.03.19 use same code style for all cases
// TODO: 02.03.19 read rest best practices
// TODO: 02.03.19 use request mapping on Contrloller level
//@RequestMapping(value = "/location")
// TODO: 02.03.19 use constructor injection and @RequiredArgsConstructor
@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value = "/locations")
    public ResponseEntity<List<Location>> locations() {

        List<Location> locations = locationService.getLocations();
        return ResponseEntity.ok(locations);

    }

    // TODO: 02.03.19 we can't return optional from controller
    @GetMapping("/locations/{id}")
    public ResponseEntity<Optional<Location>> findById(@PathVariable long id) {
        Optional<Location> byId = locationService.findById(id);
        return ResponseEntity.ok(byId);
    }

    // TODO: 02.03.19 Return exact types (return created location)
    @PostMapping("/createlocations")
    public ResponseEntity<Object> createLocation(@RequestBody Location location) {

        // TODO: 02.03.19 bad name
        Location savedlocation = locationService.save(location);

        URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedlocation.getId()).toUri();

        return ResponseEntity.created(loc).build();

    }

    // TODO: 02.03.19 use response entity
    @DeleteMapping("/removeLocations/{id}")
    public void deleteStudent(@PathVariable long id) {
        locationService.deleteById(id);
    }

    // TODO: 02.03.19 you don't need to accept id separately
    // TODO: 02.03.19 move logic to service
    @PutMapping("/updateLocations/{id}")
    public ResponseEntity<Object> updateLocation(@RequestBody Location location, @PathVariable long id) {

        Optional<Location> locationOptional = locationService.findById(id);

        if (!locationOptional.isPresent())
            return ResponseEntity.notFound().build();

        location.setId(id);

        locationService.save(location);

        return ResponseEntity.noContent().build();
    }

}
