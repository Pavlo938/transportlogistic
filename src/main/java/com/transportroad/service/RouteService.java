package com.transportroad.service;

import com.transportroad.model.domain.Route;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    Route add(Route route);

    List<Route> getAll();

    Optional<Route> findById(long id);

    void remove(long id);

}
