package com.transportroad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteTwoPointsDTO {

    private Long from;

    private Long to;

    private double distance;
}
