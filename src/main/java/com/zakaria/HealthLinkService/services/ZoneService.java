package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.ZoneRequest;
import com.zakaria.HealthLinkService.models.Zone;

import java.util.List;
import java.util.UUID;

public interface ZoneService {
    Zone add(ZoneRequest zoneRequest);
    Zone edit(UUID id, ZoneRequest zoneRequest);
    Zone get(UUID id);
    List<Zone> all();
    boolean delete(UUID id);
}
