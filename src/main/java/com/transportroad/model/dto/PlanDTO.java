package com.transportroad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO {

    private List<RouteTwoPointsDTO> route;

    private double totalDistance;

}


