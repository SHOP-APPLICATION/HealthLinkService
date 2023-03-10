package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.exceptions.ResourceNotFoundException;
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

import java.time.LocalDateTime;
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
        UUID pharmacyId = UUID.randomUUID();

        pharmacy.setId(pharmacyId);
        Pharmacy svaedPharmacy =  pharmacyRepository.save(pharmacy);
        Address address = addressService.addToPharmacy(pharmacyRequest.getAddress(), svaedPharmacy);
        svaedPharmacy.getAddresses().add(address);
        return svaedPharmacy;
    }

    @Override
    public Pharmacy edit(UUID id, PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Pharmacy not found with id: " + id.toString()));
        Address currentAddress = pharmacy.getAddresses().stream()
                    .filter(a -> a.getStatus().equals(Status.ACTIVE))
                    .findFirst()
                   .orElse(null);
        AddressRequest newAddress = pharmacyRequest.getAddress();
        if (!(currentAddress.getAddress1().equalsIgnoreCase(newAddress.getAddress1())
                && currentAddress.getStreet().equalsIgnoreCase(newAddress.getStreet())
                && currentAddress.getZipCode().equals(newAddress.getZipCode())
                && currentAddress.getCity().getId().equals(newAddress.getCity())
                && currentAddress.getZone().getId().equals(newAddress.getZone()))
        ) {
            addressService.delete(currentAddress.getId());
            Address address = addressService.addToPharmacy(newAddress, pharmacy);
            pharmacy.getAddresses().add(address);
        }
        pharmacy.setName(pharmacyRequest.getName());
        pharmacy.setPhone(pharmacyRequest.getPhone());
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Pharmacy get(UUID id) {
        return pharmacyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Pharmacy not found with id: " + id.toString()));
    }

    @Override
    public List<Pharmacy> all() {
        return pharmacyRepository.findAll();
    }

    @Override
    public boolean delete(UUID id) {

        Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Pharmacy not found with id: " + id.toString()));
        pharmacy.setDeletedAt(LocalDateTime.now());
        pharmacy.setStatus(Status.INACTIVE);
        pharmacyRepository.save(pharmacy);
        return true;
    }
}
