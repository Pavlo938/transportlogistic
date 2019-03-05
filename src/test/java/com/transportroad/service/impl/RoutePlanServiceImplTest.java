package com.transportroad.service.impl;

import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.model.dto.PlanDTO;
import com.transportroad.model.dto.RouteTwoPointsDTO;
import com.transportroad.repository.LocationRepository;
import com.transportroad.repository.RouteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class RoutePlanServiceImplTest {

    @InjectMocks
    @Spy
    private RoutePlanServiceImpl routePlanService;

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private LocationRepository locationRepository;

    @Test
    public void test_getPlan_whenCorrectInput_thenPass(){

        List<Long> locationIds = Arrays.asList(1L, 2L, 3L);

        List<Location> locations = Arrays.asList(
                new Location("Kyiv", 10.0, 15.5),
                new Location("Pekin", 50, 80),
                new Location("Lyon", 51, 81));

        doReturn(locations).when(routePlanService).locationsValidator(locationIds);

        PlanDTO planDTO = new PlanDTO();

        List<RouteTwoPointsDTO> routeTwoPointsDTO = Arrays.asList(
                new RouteTwoPointsDTO(1L, 2L, 75.89631084578485),
                new RouteTwoPointsDTO(2L, 3L, 1.4142135623730951));

        planDTO.setRoute(routeTwoPointsDTO);
        planDTO.setTotalDistance(77.31052440815795);

        doReturn(planDTO).when(routePlanService).createPlanDTO(locations);

        PlanDTO actual = this.routePlanService.getPlan(locationIds);

        assertEquals(planDTO, actual);


    }

    @Test
    public void test_getPlanById() {

        long routeId = 1;

        List<Location> locations = Arrays.asList(
                new Location(1L,"Kyiv", 10.0, 15.5),
                new Location(2L,"Pekin", 50, 80),
                new Location(3L,"Lyon", 51, 81));

        Route route = new Route("CoolTrip", locations);

        doReturn(Optional.of(route)).when(routeRepository).findById(routeId);

        PlanDTO expected = new PlanDTO();

        List<RouteTwoPointsDTO> routeTwoPointsDTO = Arrays.asList(

                new RouteTwoPointsDTO(2L, 3L, 1.4142135623730951),
                new RouteTwoPointsDTO(1L, 2L, 75.89631084578485));

        expected.setRoute(routeTwoPointsDTO);
        expected.setTotalDistance(77.31052440815795);

        PlanDTO actual = routePlanService.getPlan(routeId);

        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void test_getPlanById_ThrowingException(){

        long routeId = 4;

        doReturn(Optional.empty()).when(routeRepository).findById(routeId);

        routePlanService.getPlan(routeId);

    }

    @Test
    public void test_CreatePlanDTO(){



    }

}