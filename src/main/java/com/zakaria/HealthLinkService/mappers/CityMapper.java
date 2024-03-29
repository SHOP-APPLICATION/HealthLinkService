package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.CityAddress;
import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.dto.CityResponse;
import com.zakaria.HealthLinkService.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CityMapper {

    private final ModelMapper modelMapper;
    private final ZoneMapper zoneMapper;

    public CityMapper(ModelMapper modelMapper,ZoneMapper zoneMapper) {

        this.modelMapper = modelMapper;
        this.zoneMapper = zoneMapper;
    }

    public CityResponse toDto(City city) {
        CityResponse cityDto = modelMapper.map(city, CityResponse.class);
        cityDto.setZone(zoneMapper.toDto(city.getZone()));
        return cityDto;
    }
    public CityAddress toCityAddressDto(City city) {
        CityAddress cityAddress = modelMapper.map(city, CityAddress.class);
        return cityAddress;
    }

    public City toEntity(CityRequest cityDto) {
        return modelMapper.map(cityDto, City.class);
    }

}
