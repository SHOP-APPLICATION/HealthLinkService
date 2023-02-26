package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.mappers.PharmacyMapper;
import com.zakaria.HealthLinkService.mappers.ZoneMapper;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.Pharmacy;
import com.zakaria.HealthLinkService.models.Specialty;
import com.zakaria.HealthLinkService.repositories.AddressRepository;
import com.zakaria.HealthLinkService.repositories.PharmacyRepository;
import com.zakaria.HealthLinkService.repositories.ZoneRepository;
import com.zakaria.HealthLinkService.services.PharmacyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class PharmacyServiceImpl implements PharmacyService {

    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PharmacyMapper pharmacyMapper;


    @Override
    public PharmacyResponse add(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyMapper.toEntity(pharmacyRequest);
        Address address = addressRepository.save(pharmacy.getAddress())
        return specialtyMapper.toDto(specialtyRepository.save(specialty));
    }

    @Override
    public PharmacyResponse edit(UUID id, PharmacyRequest pharmacyRequest) {
        return null;
    }

    @Override
    public PharmacyResponse get(UUID id) {
        return null;
    }

    @Override
    public List<PharmacyResponse> all() {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
