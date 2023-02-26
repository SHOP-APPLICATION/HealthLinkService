package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.models.Pharmacy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PharmacyMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressMapper addressMapper;


    public PharmacyResponse toDto(Pharmacy pharmacy) {
        PharmacyResponse pharmacyResponse = modelMapper.map(pharmacy, PharmacyResponse.class);
        pharmacyResponse.setAddressDto(
                addressMapper.toDto(
                        pharmacy.getAddresses().stream()
                                .filter(a -> a.getStatus().equals(Status.ACTIVE))
                                .findFirst()
                                .orElse(null)
                )
        );
        return pharmacyResponse;

    }

    public Pharmacy toEntity(PharmacyRequest pharmacyDto) {
        return modelMapper.map(pharmacyDto, Pharmacy.class);
    }

}
