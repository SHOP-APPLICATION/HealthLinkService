package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.DoctorRequest;
import com.zakaria.HealthLinkService.dto.DoctorResponse;
import com.zakaria.HealthLinkService.dto.DoctorResponse;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.models.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorMapper {

    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private AddressMapper addressMapper;

    public DoctorResponse toDto(Doctor doctor) {
        DoctorResponse doctorResponse = modelMapper.map(doctor, DoctorResponse.class);
        doctorResponse.setAddressDto(
                addressMapper.toDto(
                        doctor.getAddresses().stream()
                                .filter(a -> a.getStatus().equals(Status.ACTIVE))
                                .findFirst()
                                .orElse(null)
                )
        );
        return doctorResponse;
    }

    public Doctor toEntity(DoctorRequest doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }

}
