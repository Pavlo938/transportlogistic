package com.transportroad.service.impl;

import com.transportroad.exception.LocationNotFoundException;
import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.model.dto.PlanDTO;
import com.transportroad.model.dto.RouteTwoPointsDTO;
import com.transportroad.repository.LocationRepository;
import com.transportroad.repository.RouteRepository;
import com.transportroad.service.RoutePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RoutePlanServiceImpl implements RoutePlanService {

    private final RouteRepository routeRepository;

    private final LocationRepository locationRepository;

    @Override
    public PlanDTO getPlan(List<Long> locationIds) {
        List<Location> locations = this.locationsValidator(locationIds);

        PlanDTO planDTO = this.createPlanDTO(locations);
        planDTO.getRoute().sort(Comparator.comparingDouble(RouteTwoPointsDTO::getDistance));

        return planDTO;
    }

    @Override
    @Transactional
    public PlanDTO getPlan(long routeId) {

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        PlanDTO planDTO = this.createPlanDTO(route.getLocations());
        planDTO.getRoute().sort(Comparator.comparingDouble(RouteTwoPointsDTO::getDistance));

        return planDTO;
    }

    PlanDTO createPlanDTO(List<Location> locations) {

        List<RouteTwoPointsDTO> routeDTO = new ArrayList<>();

        double totalDistance = 0;

        for (int i = 1; i < locations.size(); i++) {

            Location from = locations.get(i - 1);
            Location to = locations.get(i);

            double distance = this.distance(from.getX(), from.getY(), to.getX(), to.getY());

            RouteTwoPointsDTO routeTwoPointsDTO = new RouteTwoPointsDTO(from.getId(), to.getId(), distance);
            totalDistance += distance;

            routeDTO.add(routeTwoPointsDTO);
        }

        return new PlanDTO(routeDTO, totalDistance);
    }

    double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    List<Location> locationsValidator(List<Long> list) {

        Set<Long> uniqueLocationIds = new HashSet<>(list);
        List<Location> locationsByIdIn = locationRepository.getLocationsByIdIn(list);

        if (!(uniqueLocationIds.size() == locationsByIdIn.size())) {
            throw new LocationNotFoundException("Location is not found");
        } else return locationsByIdIn;
    }
}
