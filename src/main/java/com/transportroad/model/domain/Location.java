package com.transportroad.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

//    todo: remove this
    /*
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id", nullable = false)

     @ManyToMany(mappedBy = "locations")
    List<Route> routes;
*/
    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

//    todo: use lombock
    public Location() {
    }
}
