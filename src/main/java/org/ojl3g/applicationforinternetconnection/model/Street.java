package org.ojl3g.applicationforinternetconnection.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "streets")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String streetName;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;
}
