package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.ZoneRequest;
import com.zakaria.HealthLinkService.dto.ZoneResponse;
import com.zakaria.HealthLinkService.models.Zone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZoneMapper {
    @Autowired
    private ModelMapper modelMapper;

    public ZoneResponse toDto(Zone zone) {
        return modelMapper.map(zone, ZoneResponse.class);
    }

    public Zone toEntity(ZoneRequest zoneDto) {
        return modelMapper.map(zoneDto, Zone.class);
    }

}
