package com.transportroad.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "ROUTE")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // TODO: 02.03.19 useless here
    @Column(name = "NAME")
    private String name;


    // TODO: 02.03.19 many to many should have private setter
    // TODO: 02.03.19 use one code convention for all db namings
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
        name = "Loc_Route",
        joinColumns = @JoinColumn(name = "ROUTE_ID"),
        inverseJoinColumns = @JoinColumn(name = "LOCATION_ID")
    )
    private List<Location> locations;


    // TODO: 02.03.19 same as for location
    public Route() {
    }

    public Route(String name, List <Location> locations) {
        this.name = name;
        this.locations = locations;
    }

    /*
    public Route(String name, Location head, Set<Location> locations) {
        this.name = name;
        this.head= head;
        this.locations = locations;
    }


    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "route")

    private Location head;

    //private Location[] locations;
    //@ManyToMany
    //private Set<Location> locations;

   */


}
