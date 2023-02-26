package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.dto.SpecialtyResponse;
import com.zakaria.HealthLinkService.services.SpecialtyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/link/specialties")
@Slf4j
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;
    @PostMapping()
    @PreAuthorize("hasAuthority('SPECIALTIES')")
    public ResponseEntity<SpecialtyResponse> save(@Valid @RequestBody SpecialtyRequest requestDTO){

        /* return ResponseEntity.ok(added);*/
        return new ResponseEntity<>(specialtyService.add(requestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> edit(@Valid @RequestBody SpecialtyRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(specialtyService.edit(id, requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        specialtyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> get(@PathVariable UUID id){
       return new ResponseEntity<>(specialtyService.get(id), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('SPECIALTIES')")
    public ResponseEntity<List<SpecialtyResponse>> getAll () {
        return  ResponseEntity.ok(specialtyService.all());
    }
}
