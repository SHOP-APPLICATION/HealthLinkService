package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.dto.PharmacyRequest;
import com.zakaria.HealthLinkService.dto.PharmacyResponse;
import com.zakaria.HealthLinkService.mappers.PharmacyMapper;
import com.zakaria.HealthLinkService.mappers.PharmacyMapper;
import com.zakaria.HealthLinkService.services.PharmacyService;
import com.zakaria.HealthLinkService.services.PharmacyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/link/pharmacies")
@Slf4j
public class PharmacyController {
    @Autowired
    private PharmacyService pharmacyService;
    @Autowired
    private PharmacyMapper pharmacyMapper;

    @PostMapping()
    public ResponseEntity<PharmacyResponse> save(@Valid @RequestBody PharmacyRequest requestDTO){
        return new ResponseEntity<>(pharmacyMapper.toDto(pharmacyService.add(requestDTO)), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PharmacyResponse> edit(@Valid @RequestBody PharmacyRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(pharmacyMapper.toDto(pharmacyService.edit(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        pharmacyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PharmacyResponse> get(@PathVariable UUID id){
        log.info("ok ... OK");
        System.out.println("her iam i ");
        return new ResponseEntity<>(pharmacyMapper.toDto(pharmacyService.get(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PharmacyResponse>> getAll () {
        List<PharmacyResponse> pharmacies = pharmacyService.all().stream().map(pharmacyMapper::toDto).collect(Collectors.toList());
        return new ResponseEntity<>(pharmacies, HttpStatus.OK);
    }
}