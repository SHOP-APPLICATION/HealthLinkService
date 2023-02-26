package com.zakaria.HealthLinkService.services.impl;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.dto.SpecialtyResponse;
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
    public SpecialtyResponse add(SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyMapper.toEntity(specialtyRequest);
        return specialtyMapper.toDto(specialtyRepository.save(specialty));
    }

    @Override
    public SpecialtyResponse edit(UUID id, SpecialtyRequest specialtyRequest) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty not found with id: " + id.toString()));
        specialty.setName(specialtyRequest.getName());
        return specialtyMapper.toDto(specialtyRepository.save(specialty));
    }

    @Override
    public SpecialtyResponse get(UUID id) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Specialty not found with id: " + id.toString()));
        return specialtyMapper.toDto(specialty);
    }

    @Override
    public List<SpecialtyResponse> all() {
        List<Specialty> specialtys = specialtyRepository.findAll();
        return specialtys.stream().map(specialtyMapper::toDto).collect(Collectors.toList());
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