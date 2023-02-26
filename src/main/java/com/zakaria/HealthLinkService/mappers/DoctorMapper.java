package com.zakaria.HealthLinkService.mappers;

import com.zakaria.HealthLinkService.dto.DoctorRequest;
import com.zakaria.HealthLinkService.dto.DoctorResponse;
import com.zakaria.HealthLinkService.models.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorMapper {

    @Autowired
    private  ModelMapper modelMapper;



    public DoctorResponse toDto(Doctor doctor) {
        return modelMapper.map(doctor, DoctorResponse.class);
    }

    public Doctor toEntity(DoctorRequest doctorDto) {
        return modelMapper.map(doctorDto, Doctor.class);
    }

}
