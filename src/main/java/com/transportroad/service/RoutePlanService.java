package com.transportroad.service;

import com.transportroad.model.domain.Location;
import com.transportroad.model.dto.PlanDTO;

import java.util.List;

public interface RoutePlanService {

    PlanDTO getPlan(long routeId);

    PlanDTO getPlan(List<Location> locations);

    double distance (double x1, double y1, double x2, double y2);

}
