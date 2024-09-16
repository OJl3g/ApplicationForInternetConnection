package org.ojl3g.applicationforinternetconnection.repository;

import org.ojl3g.applicationforinternetconnection.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
