package org.ojl3g.applicationforinternetconnection.service;

import org.ojl3g.applicationforinternetconnection.model.Application;
import org.ojl3g.applicationforinternetconnection.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void saveApplication(Application application) {
        applicationRepository.save(application);

    }
}
