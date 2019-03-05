package com.transportroad;

import com.transportroad.model.domain.Location;
import com.transportroad.model.domain.Route;
import com.transportroad.repository.LocationRepository;
import com.transportroad.repository.RouteRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Bean
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();
    }

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    RouteRepository routeRepository;

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

    @Override
    public void run(String... args) throws Exception {

        List<Location> locations = Arrays.asList(
                new Location("Kiev", 10.0, 15.5),
                new Location("London", 50, 80),
                new Location("Paris", 51, 81));

        locationRepository.saveAll(locations);

        Route route1 = new Route("CoolTrip", locations);
        routeRepository.save(route1);

    }
}
