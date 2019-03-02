package com.transportroad.controller;

import com.transportroad.model.domain.Location;
import com.transportroad.model.dto.PlanDTO;
import com.transportroad.service.LocationService;
import com.transportroad.service.RoutePlanService;
import com.transportroad.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class RoutePlanController {

    @Autowired
    public RouteService routeService;
    @Autowired
    public LocationService locationService;
    @Autowired
    public RoutePlanService routePlanService;


    @GetMapping("/routes/{id}/plan")
    public ResponseEntity<PlanDTO> planDTOResponseEntity(@PathVariable long id) {

        return ResponseEntity.ok(routePlanService.getPlan(id));
    }

    @PostMapping("/routes/postplan/")
    public ResponseEntity<PlanDTO> planDTOResponseEntity(@RequestBody List<Long> list) throws Exception {

        Set<Long> uniqueLocationIds = new HashSet<>(list);

        List<Location> locationsByIdIn = locationService.getLocationsByIdIn(list);

        if (!(uniqueLocationIds.size() == locationsByIdIn.size())) throw new Exception("Location is not found");

        return ResponseEntity.ok(routePlanService.getPlan(locationsByIdIn));

    }
}