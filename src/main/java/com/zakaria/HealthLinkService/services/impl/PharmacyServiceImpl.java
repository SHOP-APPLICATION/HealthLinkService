package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.mappers.PharmacyMapper;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.Pharmacy;
import com.zakaria.HealthLinkService.repositories.PharmacyRepository;
import com.zakaria.HealthLinkService.services.AddressService;
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
    private AddressService addressService;
    @Autowired
    private PharmacyMapper pharmacyMapper;


    @Override
    public Pharmacy add(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyMapper.toEntity(pharmacyRequest);
        Address address = addressService.add(pharmacyRequest.getAddress());
        pharmacy.setAddress(address);
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy edit(UUID id, PharmacyRequest pharmacyRequest) {
        return null;
    }

    @Override
    public Pharmacy get(UUID id) {
        return null;
    }

    @Override
    public List<Pharmacy> all() {
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
