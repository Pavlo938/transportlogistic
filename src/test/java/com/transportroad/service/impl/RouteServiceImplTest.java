package com.transportroad.service.impl;

import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.repository.RouteRepository;
import com.transportroad.service.RouteService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    @Spy
    @InjectMocks
    RouteServiceImpl routeService;

    @Mock
    RouteRepository routeRepository;

    @Test
    public void test_getAll_routes() {

        List<Location> locations = Arrays.asList(
                new Location("Kyiv", 10.0, 15.5),
                new Location("Pekin", 50, 80),
                new Location("Lyon", 51, 81));

        Route route = new Route("CoolTrip",locations );

        List<Route> expectedRoutes = new ArrayList<>();
            expectedRoutes.add(route);

             doReturn( expectedRoutes).when(routeRepository).findAll();

        List<Route> actualRoutes = routeService.getAll();

        Assert.assertEquals(expectedRoutes,actualRoutes);

    }

}