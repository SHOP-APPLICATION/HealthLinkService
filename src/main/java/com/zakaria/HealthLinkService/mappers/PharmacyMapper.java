package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.AddressDto;
import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.Pharmacy;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration

@Slf4j
public class PharmacyMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressMapper addressMapper;


    public PharmacyResponse toDto(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = modelMapper.map(pharmacy, PharmacyResponse.class);
        Address address = pharmacy.getAddresses().stream()
                .filter(a -> a.getStatus().equals(Status.ACTIVE))
                .findFirst()
                .orElse(null);
        log.info(address.getStreet());
        pharmacyResponse.setAddress(addressMapper.toDto(address));
        return pharmacyResponse;

    }

    public Pharmacy toEntity(PharmacyRequest pharmacyDto) {
        return modelMapper.map(pharmacyDto, Pharmacy.class);
    }

}
