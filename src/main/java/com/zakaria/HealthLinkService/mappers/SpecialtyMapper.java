package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.dto.SpecialtyResponse;
import com.zakaria.HealthLinkService.models.Specialty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecialtyMapper {
    @Autowired
    private ModelMapper modelMapper;

    public SpecialtyResponse toDto(Specialty specialty) {
        return modelMapper.map(specialty, SpecialtyResponse.class);
    }

    public Specialty toEntity(SpecialtyRequest specialtyDto) {
        return modelMapper.map(specialtyDto, Specialty.class);
    }

}
