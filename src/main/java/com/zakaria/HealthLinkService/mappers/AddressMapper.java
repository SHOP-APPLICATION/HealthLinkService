package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.AddressDto;
import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.dto.CityResponse;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.City;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    private final ModelMapper modelMapper;
    private ZoneMapper zoneMapper;
    private CityMapper cityMapper;

    public AddressMapper(ModelMapper modelMapper,ZoneMapper zoneMapper, CityMapper cityMapper) {

        this.modelMapper = modelMapper;
        this.zoneMapper = zoneMapper;
        this.cityMapper = cityMapper;
    }

    public AddressDto toDto(Address address) {
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        addressDto.setZone(zoneMapper.toDto(address.getZone()));
        addressDto.setCity(cityMapper.toDto(address.getCity()));
        return addressDto;
    }

    public Address toEntity(AddressRequest request) {
        return modelMapper.map(request, Address.class);
    }

}
