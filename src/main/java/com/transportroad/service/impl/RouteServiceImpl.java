package com.transportroad.service.impl;

import com.transportroad.model.domain.Route;
import com.transportroad.repository.RouteRepository;
import com.transportroad.service.LocationService;
import com.transportroad.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private LocationService locationService;

    @Override
    public Route add(Route route) {
        routeRepository.save(route);
        return route;
    }

    @Override
    public List<Route> getAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> findById(long id) {
        return routeRepository.findById(id);
    }


    @Override
    public void remove(long id) {
        routeRepository.deleteById(id);
    }


}
