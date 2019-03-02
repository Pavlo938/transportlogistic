package com.transportroad.service.impl;

import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.model.dto.PlanDTO;
import com.transportroad.model.dto.RouteTwoPointsDTO;
import com.transportroad.repository.RouteRepository;
import com.transportroad.service.RoutePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RoutePlanServiceImpl implements RoutePlanService {

    @Autowired
    private RouteRepository routeRepository;

    // TODO: 02.03.19 move common logic to separate function
    @Override
    public PlanDTO getPlan(List<Location> locations) {

        double totalDistance = 0;

        List<RouteTwoPointsDTO> routeDTO = new ArrayList<>();
        for (int i = 1; i < locations.size(); i++) {

            Location from = locations.get(i - 1);
            Location to = locations.get(i);


            double distance = this.distance(from.getX(), from.getY(), to.getX(), to.getY());

            RouteTwoPointsDTO routeTwoPointsDTO = new RouteTwoPointsDTO(from.getId(), to.getId(), distance);
            distance += distance;

            totalDistance += distance;
            routeDTO.add(routeTwoPointsDTO);
        }
        routeDTO.sort(Comparator.comparingDouble(RouteTwoPointsDTO::getDistance));


        return new PlanDTO(routeDTO, totalDistance);


    }


    @Override
    public PlanDTO getPlan(long routeId) {

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        double totalDistance = 0;
        List<RouteTwoPointsDTO> routeDto = new ArrayList<>();

        for (int i = 1; i < route.getLocations().size(); i++) {

            Location from = route.getLocations().get(i - 1);
            Location to = route.getLocations().get(i);


            double distance = distance(from.getX(), from.getY(), to.getX(), to.getY());

            RouteTwoPointsDTO routeTwoPointsDTO = new RouteTwoPointsDTO(
                    from.getId(),
                    to.getId(),
                    distance);

            totalDistance += distance;
            routeDto.add(routeTwoPointsDTO);
        }
        routeDto.sort(Comparator.comparingDouble(RouteTwoPointsDTO::getDistance));


        return new PlanDTO(routeDto, totalDistance);
    }

    @Override
    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
