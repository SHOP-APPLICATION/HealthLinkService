package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.ZoneResponse;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.City;
import com.zakaria.HealthLinkService.repositories.AddressRepository;
import com.zakaria.HealthLinkService.services.AddressService;
import com.zakaria.HealthLinkService.services.CityService;
import com.zakaria.HealthLinkService.services.ZoneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final CityService cityService;
    private final ZoneService zoneService;

    @Override
    public Address add(AddressRequest request) {
        City city = cityService.get(request.getCity());
        return null;
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
        return false;
    }
}
