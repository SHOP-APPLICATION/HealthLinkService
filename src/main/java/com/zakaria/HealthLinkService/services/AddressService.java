package com.zakaria.HealthLinkService.services;

import com.zakaria.HealthLinkService.dto.AddressDto;
import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.dto.CityResponse;
import com.zakaria.HealthLinkService.models.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    Address add(AddressRequest request);
    Address edit(UUID id, AddressRequest request);
    Address get(UUID id);
    boolean delete(UUID id);
}