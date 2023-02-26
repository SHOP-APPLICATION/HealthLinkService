package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.models.City;

import java.util.List;
import java.util.UUID;

public interface CityService {
    City add(CityRequest cityRequest);
    City edit(UUID id, CityRequest cityRequest);
    City get(UUID id);
    List<City> all();
    boolean delete(UUID id);
}
