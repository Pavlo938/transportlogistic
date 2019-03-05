package com.transportroad.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double x;

    @Column(nullable = false)
    private double y;

    @Setter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "locations")
    @ToString.Exclude
    @JsonIgnore
    private List<Route> routes = new ArrayList<>();

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    //test constructor for Junit Test
    public Location(Long id, String name, double x, double y){
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;

    }

}
