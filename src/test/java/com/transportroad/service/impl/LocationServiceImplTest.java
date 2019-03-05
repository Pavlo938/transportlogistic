package com.transportroad.service.impl;

import com.transportroad.model.domain.Location;
import com.transportroad.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceImplTest {

    @Spy
    @InjectMocks
    LocationServiceImpl locationService;

    @Mock
    private LocationRepository locationRepository;

    @Test
    public void test_getLocations(){

        List<Location> locations = Arrays.asList(
                new Location("Kyiv", 10.0, 15.5),
                new Location("Pekin", 50, 80),
                new Location("Lyon", 51, 81));

        when(locationRepository.findAll()).thenReturn(locations);

        List<Location> actual = locationService.getLocations();

        assertEquals(locations, actual);

    }
}