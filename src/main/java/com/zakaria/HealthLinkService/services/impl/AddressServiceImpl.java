package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.ZoneResponse;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.mappers.AddressMapper;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.City;
import com.zakaria.HealthLinkService.models.Specialty;
import com.zakaria.HealthLinkService.models.Zone;
import com.zakaria.HealthLinkService.repositories.AddressRepository;
import com.zakaria.HealthLinkService.services.AddressService;
import com.zakaria.HealthLinkService.services.CityService;
import com.zakaria.HealthLinkService.services.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CityService cityService;
    private final ZoneService zoneService;

    @Override
    public Address add(AddressRequest request) {
        City city = cityService.get(request.getCity());
        Zone zone = zoneService.get(request.getZone());
        Address address = addressMapper.toEntity(request);
        address.setZone(zone);
        address.setCity(city);
        return addressRepository.save(address);
    }

    @Override
    public Address edit(UUID id, AddressRequest request) {
        return null;
    }

    @Override
    public Address get(UUID id) {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found with id: " + id.toString()));
        address.setDeletedAt(LocalDateTime.now());
        address.setStatus(Status.INACTIVE);
        addressRepository.save(address);
        return true;
    }
}
