package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.DoctorRequest;
import com.zakaria.HealthLinkService.dto.DoctorResponse;
import com.zakaria.HealthLinkService.mappers.DoctorMapper;
import com.zakaria.HealthLinkService.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/link/doctors")
@Slf4j
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private DoctorMapper doctorMapper;

    @PostMapping()
    @PreAuthorize("hasAuthority('DOCTORS')")
    public ResponseEntity<DoctorResponse> save(@Valid @RequestBody DoctorRequest requestDTO){
        return new ResponseEntity<>(doctorMapper.toDto(doctorService.add(requestDTO)), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> edit(@Valid @RequestBody DoctorRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(doctorMapper.toDto(doctorService.edit(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> get(@PathVariable UUID id){
        return new ResponseEntity<>(doctorMapper.toDto(doctorService.get(id)), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('DOCTORS')")
    public ResponseEntity<List<DoctorResponse>> getAll () {
        List<DoctorResponse> doctors = doctorService.all().stream().map(doctorMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(doctors);
    }
}
