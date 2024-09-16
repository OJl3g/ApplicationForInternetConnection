package org.ojl3g.applicationforinternetconnection.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "applications")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String house;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String tariff;

    @Column(nullable = false)
    private Date dateSubmitted = new Date(); // Дата подачи заявки
}
