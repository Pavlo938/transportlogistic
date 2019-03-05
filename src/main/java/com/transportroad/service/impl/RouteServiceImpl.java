package com.transportroad.service.impl;

import com.transportroad.model.domain.Route;
import com.transportroad.repository.RouteRepository;
import com.transportroad.service.LocationService;
import com.transportroad.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    public Route add(Route route) {

        routeRepository.save(route);
        return route;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Route> getAll() {

        return (List<Route>) routeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Route> findById(long id) {

        return routeRepository.findById(id);
    }

    @Override
    public void remove(long id) {

        routeRepository.deleteById(id);
    }


}
