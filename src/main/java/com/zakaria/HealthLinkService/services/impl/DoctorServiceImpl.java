package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.AddressRequest;
import com.zakaria.HealthLinkService.dto.DoctorRequest;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.exceptions.ResourceNotFoundException;
import com.zakaria.HealthLinkService.mappers.DoctorMapper;
import com.zakaria.HealthLinkService.models.Address;
import com.zakaria.HealthLinkService.models.Doctor;
import com.zakaria.HealthLinkService.models.Specialty;
import com.zakaria.HealthLinkService.repositories.DoctorRepository;
import com.zakaria.HealthLinkService.services.AddressService;
import com.zakaria.HealthLinkService.services.DoctorService;
import com.zakaria.HealthLinkService.services.SpecialtyService;
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
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private DoctorMapper doctorMapper;


    @Override
    public Doctor add(DoctorRequest doctorRequest) {

        Doctor doctor = doctorMapper.toEntity(doctorRequest);
        Specialty specialty = specialtyService.get(doctorRequest.getSpecialty());

        doctor.setId(UUID.randomUUID());
        doctor.setSpecialty(specialty);
        Doctor svaedDoctor = doctorRepository.save(doctor);
        Address address = addressService.addToDoctor(doctorRequest.getAddress(), svaedDoctor);
        svaedDoctor.getAddresses().add(address);
        return svaedDoctor;
    }

    @Override
    public Doctor edit(UUID id, DoctorRequest doctorRequest) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Doctor not found with id: " + id.toString()));
        Specialty specialty = specialtyService.get(doctorRequest.getSpecialty());
        Address currentAddress = doctor.getAddresses().stream()
                .filter(a -> a.getStatus().equals(Status.ACTIVE))
                .findFirst()
                .orElse(null);
        AddressRequest newAddress = doctorRequest.getAddress();
        if (!(currentAddress.getAddress1().equalsIgnoreCase(newAddress.getAddress1())
                && currentAddress.getStreet().equalsIgnoreCase(newAddress.getStreet())
                && currentAddress.getZipCode().equals(newAddress.getZipCode())
                && currentAddress.getCity().getId().equals(newAddress.getCity())
                && currentAddress.getZone().getId().equals(newAddress.getZone()))
        ) {
            addressService.delete(currentAddress.getId());
            Address address = addressService.addToDoctor(doctorRequest.getAddress(), doctor);
            doctor.getAddresses().add(address);
        }
        doctor.setSpecialty(specialty);
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor get(UUID id) {
        return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Doctor not found with id: " + id.toString()));
    }

    @Override
    public List<Doctor> all() {
        return doctorRepository.findAll();
    }

    @Override
    public boolean delete(UUID id) {

        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Doctor not found with id: " + id.toString()));
        doctor.setDeletedAt(LocalDateTime.now());
        doctor.setStatus(Status.INACTIVE);
        doctorRepository.save(doctor);
        return true;
    }
}
