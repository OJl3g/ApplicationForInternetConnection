package org.ojl3g.applicationforinternetconnection.repository;

import org.ojl3g.applicationforinternetconnection.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreetRepository extends JpaRepository<Street, Long> {
    List<Street> findAllByCity_Id(Long id);
}
