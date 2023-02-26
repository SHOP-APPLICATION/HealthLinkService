package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.models.Pharmacy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PharmacyMapper {
    @Autowired
    private ModelMapper modelMapper;



    public PharmacyResponse toDto(Pharmacy pharmacy) {
        return modelMapper.map(pharmacy, PharmacyResponse.class);
    }

    public Pharmacy toEntity(PharmacyRequest pharmacyDto) {
        return modelMapper.map(pharmacyDto, Pharmacy.class);
    }

}
