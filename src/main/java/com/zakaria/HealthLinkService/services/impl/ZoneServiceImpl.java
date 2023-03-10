package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.ZoneRequest;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.exceptions.ResourceNotFoundException;
import com.zakaria.HealthLinkService.mappers.ZoneMapper;
import com.zakaria.HealthLinkService.models.Zone;
import com.zakaria.HealthLinkService.repositories.ZoneRepository;
import com.zakaria.HealthLinkService.services.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ZoneServiceImpl implements ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private ZoneMapper zoneMapper;
    @Override
    public Zone add(ZoneRequest zoneRequest) {
        Zone zone = zoneMapper.toEntity(zoneRequest);
        zone.setId(UUID.randomUUID());
        return zoneRepository.save(zone);
    }

    @Override
    public Zone edit(UUID id, ZoneRequest zoneRequest) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id.toString()));
        zone.setName(zoneRequest.getName());
        return zoneRepository.save(zone);
    }

    @Override
    public Zone get(UUID id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id.toString()));
        return zone;
    }

    @Override
    public List<Zone> all() {
        // List<Zone> zones = zoneRepository.findAll();
       // return zones.stream().map(zoneMapper::toDto).collect(Collectors.toList());
        return zoneRepository.findAll();
    }

    @Override
    public boolean delete(UUID id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Zone not found with id: " + id.toString()));
        zone.setDeletedAt(LocalDateTime.now());
        zone.setStatus(Status.INACTIVE);
        zoneRepository.save(zone);
        return true;
    }
}
