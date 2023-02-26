package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.models.Specialty;

import java.util.List;
import java.util.UUID;

public interface SpecialtyService {
    Specialty add(SpecialtyRequest specialtyRequest);
    Specialty edit(UUID id, SpecialtyRequest specialtyRequest);
    Specialty get(UUID id);
    List<Specialty> all();
    boolean delete(UUID id);
}
