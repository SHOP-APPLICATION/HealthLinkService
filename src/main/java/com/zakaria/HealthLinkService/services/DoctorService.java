package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.DoctorRequest;
import com.zakaria.HealthLinkService.models.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Doctor add(DoctorRequest doctorRequest);
    Doctor edit(UUID id, DoctorRequest doctorRequest);
    Doctor get(UUID id);
    List<Doctor> all();
    boolean delete(UUID id);
}
