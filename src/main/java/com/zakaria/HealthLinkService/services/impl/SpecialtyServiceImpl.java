package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.enums.Status;
import com.zakaria.HealthLinkService.mappers.SpecialtyMapper;
import com.zakaria.HealthLinkService.models.Specialty;
import com.zakaria.HealthLinkService.repositories.SpecialtyRepository;
import com.zakaria.HealthLinkService.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@Transactional
@Slf4j
public class SpecialtyServiceImpl implements SpecialtyService {
    @Autowired
    private SpecialtyRepository specialtyRepository;
    @Autowired
    private SpecialtyMapper specialtyMapper;

    @Override
    public Specialty add(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyMapper.toEntity(specialtyRequest);
        // specialtyMapper.toDto(specialtyRepository.save(specialty))
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty edit(UUID id, SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty not found with id: " + id.toString()));
        specialty.setName(specialtyRequest.getName());
        return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty get(UUID id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty not found with id: " + id.toString()));
        return specialty;
    }

    @Override
    public List<Specialty> all() {
        return specialtyRepository.findAll();
    }

    @Override
    public boolean delete(UUID id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty not found with id: " + id.toString()));
        specialty.setDeletedAt(LocalDateTime.now());
        specialty.setStatus(Status.INACTIVE);
        specialtyRepository.save(specialty);
        return true;
    }
}