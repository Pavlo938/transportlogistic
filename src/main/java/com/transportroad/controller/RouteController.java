package com.transportroad.controller;

import com.transportroad.model.domain.Route;
import com.transportroad.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping(value = "/all_routes")
    public ResponseEntity<List<Route>> routes(){
        List<Route> routes = routeService.getAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping(value = "/routes/{id}")
    public ResponseEntity<Optional<Route>> findById(@PathVariable long id){

        Optional<Route> byId = routeService.findById(id);
        return ResponseEntity.ok(byId);

    }

    @PutMapping("/updateRoute/{id}")
    public ResponseEntity<Route> updateRoute(@RequestBody Route route, @PathVariable long id) {

        Optional<Route> routeOptional = routeService.findById(id);

        if (!routeOptional.isPresent())
            return ResponseEntity.notFound().build();
        route.setId(id);
        routeService.add(route);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/routes/")
    public ResponseEntity<Object> createRoute(@RequestBody Route route) {

        Route savedRoute = routeService.add(route);
        URI loc = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRoute.getId()).toUri();

        return ResponseEntity.created(loc).build();

    }

    @DeleteMapping("routes/{id}")
    public ResponseEntity<Route> deleteRoute(@PathVariable long id){
        routeService.remove(id);
        return ResponseEntity.noContent().build();

    }
}
