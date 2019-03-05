package com.transportroad.controller;

import com.transportroad.model.dto.PlanDTO;
import com.transportroad.service.RoutePlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class RoutePlanController {

    private final RoutePlanService routePlanService;

    @GetMapping("/{id}")
    public ResponseEntity<PlanDTO> planDTOResponseEntity(@PathVariable long id) {

        return ResponseEntity.ok(routePlanService.getPlan(id));
    }

    @PostMapping("/")
    public ResponseEntity<PlanDTO> planDTOResponseEntity(@RequestBody List<Long> list) {

        return ResponseEntity.ok(routePlanService.getPlan(list));

    }
}