package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.exceptions.ResourceNotFoundException;
import com.zakaria.HealthLinkService.mappers.CityMapper;
import com.zakaria.HealthLinkService.models.City;
import com.zakaria.HealthLinkService.models.Zone;
import com.zakaria.HealthLinkService.repositories.CityRepository;
import com.zakaria.HealthLinkService.repositories.ZoneRepository;
import com.zakaria.HealthLinkService.services.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ZoneRepository zoneRepository;
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City add(CityRequest cityRequest) {
        Zone zone = zoneRepository.findById(cityRequest.getZone())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + cityRequest.getZone().toString()));

        City city = cityMapper.toEntity(cityRequest);
        city.setZone(zone);
        city.setId(UUID.randomUUID());
       return cityRepository.save(city);
    }

    @Override
    public City edit(UUID id, CityRequest cityRequest) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "City not found with id " + id.toString()));

        Zone zone = zoneRepository.findById(cityRequest.getZone())
                .orElseThrow(() -> new ResourceNotFoundException( "Zone not found with id " + cityRequest.getZone().toString()));
        city.setName(cityRequest.getName());
        city.setZone(zone);
        return cityRepository.save(city);
    }

    @Override
    public City get(UUID id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "City not found with id " + id.toString()));

        return city;
    }

    @Override
    public List<City> all() {
        return cityRepository.findAll();
    }

    @Override
    public boolean delete(UUID id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "city not found with id: " + id.toString()));
        city.setDeletedAt(LocalDateTime.now());
        city.setStatus(Status.INACTIVE);
        cityRepository.save(city);
        return true;
    }
}
