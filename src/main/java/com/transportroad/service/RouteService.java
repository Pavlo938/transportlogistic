package com.transportroad.service;

import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.model.dto.PlanDTO;

import java.util.List;
import java.util.Optional;

public interface RouteService {

    //create
    Route add(Route route);

    //read
    List<Route> getAll();
    Optional<Route> findById(long id);

    //delete
    void remove(long id);



}
