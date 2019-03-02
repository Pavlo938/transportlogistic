package com.transportroad.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteTwoPointsDTO {
    private long from;
    private long to;
    private double distance;



}
