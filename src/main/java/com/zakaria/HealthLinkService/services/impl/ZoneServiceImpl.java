package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.ZoneRequest;
import com.zakaria.HealthLinkService.dto.ZoneResponse;
import com.zakaria.HealthLinkService.enums.Status;
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
    public ZoneResponse add(ZoneRequest zoneRequest) {
        Zone zone = zoneMapper.toEntity(zoneRequest);
        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    @Override
    public ZoneResponse edit(UUID id, ZoneRequest zoneRequest) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Zone not found with id: " + id.toString()));
        zone.setName(zoneRequest.getName());
        return zoneMapper.toDto(zoneRepository.save(zone));
    }

    @Override
    public ZoneResponse get(UUID id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Zone not found with id: " + id.toString()));
        return zoneMapper.toDto(zone);
    }

    @Override
    public List<ZoneResponse> all() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream().map(zoneMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean delete(UUID id) {
        Zone zone = zoneRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone not found with id: " + id.toString()));
        zone.setDeletedAt(LocalDateTime.now());
        zone.setStatus(Status.INACTIVE);
        zoneRepository.save(zone);
        return true;
    }
}
