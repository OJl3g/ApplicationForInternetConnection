package org.ojl3g.applicationforinternetconnection.service;

import org.ojl3g.applicationforinternetconnection.model.Street;
import org.ojl3g.applicationforinternetconnection.repository.StreetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService {
    private final StreetRepository streetRepository;

    public StreetService(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

//    public List<Street> getStreetByIdCity() {
//        return streetRepository.findAll();
//    }

    public List<Street> getStreetByIdCity(Long id) {
        return streetRepository.findAllByCity_Id(id);
    }
}
