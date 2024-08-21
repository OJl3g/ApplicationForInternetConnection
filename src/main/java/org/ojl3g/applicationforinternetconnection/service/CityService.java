package org.ojl3g.applicationforinternetconnection.service;

import org.ojl3g.applicationforinternetconnection.model.City;
import org.ojl3g.applicationforinternetconnection.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCity() {
        return cityRepository.findAll();

    }

}
