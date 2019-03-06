package com.transportroad.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Table(name = "ROUTE")
@Builder
@AllArgsConstructor
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "LOC_ROUTE",
        joinColumns = @JoinColumn(name = "ROUTE_ID"),
        inverseJoinColumns = @JoinColumn(name = "LOCATION_ID")
    )
    @ToString.Exclude
    @JsonIgnore
    private List<Location> locations = new ArrayList<>();

    public void addLocation(Location location) {
        locations.add(location);
        location.getRoutes().add(this);
    }

    public void removeLocation(Location location){
        locations.remove(location);
        location.getRoutes().remove(this);
    }

    public Route(String name, List <Location> locations) {
        this.name = name;
        this.locations = locations;
    }


}
