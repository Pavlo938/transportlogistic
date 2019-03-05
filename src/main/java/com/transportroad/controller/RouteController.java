package com.transportroad.controller;

import com.transportroad.exception.RouteNotFoundException;
import com.transportroad.model.domain.Route;
import com.transportroad.service.RouteService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/routes")

public class RouteController {

    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> routes(){

        List<Route> routes = routeService.getAll();
            return ResponseEntity.ok(routes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Route> findById(@PathVariable long id){

        Route route = routeService.findById(id).orElseThrow(()-> new RouteNotFoundException("Route is not found"));
            return ResponseEntity.ok(route);
    }

    @PutMapping
    public ResponseEntity<Route> updateRoute(@RequestBody Route route) {

        Route newRoute = routeService.findById(route.getId()).orElseThrow(() ->
                new RouteNotFoundException("Cannot update route with such id " + route.getId()) );
                routeService.add(newRoute);
                    return ResponseEntity.ok(newRoute);
    }

    @PostMapping
    public ResponseEntity<Object> createRoute(@RequestBody Route route) {

        Route savedRoute = routeService.add(route);
            return ResponseEntity.ok(savedRoute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Route> deleteRoute(@PathVariable long id){

        routeService.remove(id);
            return ResponseEntity.noContent().build();
    }
}
