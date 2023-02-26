package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.models.Pharmacy;

import java.util.List;
import java.util.UUID;

public interface PharmacyService {
    Pharmacy add(PharmacyRequest pharmacyRequest);
    Pharmacy edit(UUID id, PharmacyRequest pharmacyRequest);
    Pharmacy get(UUID id);
    List<Pharmacy> all();
    boolean delete(UUID id);
}
