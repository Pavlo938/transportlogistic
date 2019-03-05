package com.transportroad.service;

import com.transportroad.model.dto.PlanDTO;

import java.util.List;

public interface RoutePlanService {

    PlanDTO getPlan(long routeId);

    PlanDTO getPlan(List<Long> locations);

}
