package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.SpecialtyRequest;
import com.zakaria.HealthLinkService.dto.SpecialtyResponse;
import com.zakaria.HealthLinkService.mappers.SpecialtyMapper;
import com.zakaria.HealthLinkService.services.SpecialtyService;
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
@RequestMapping("/api/link/specialties")
@Slf4j
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private SpecialtyMapper specialtyMapper;

    @PostMapping()
    public ResponseEntity<SpecialtyResponse> save(@Valid @RequestBody SpecialtyRequest requestDTO){

        /* return ResponseEntity.ok(added);*/
        return new ResponseEntity<>(specialtyMapper.toDto(specialtyService.add(requestDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> edit(@Valid @RequestBody SpecialtyRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(specialtyMapper.toDto(specialtyService.edit(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        specialtyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> get(@PathVariable UUID id){
        log.info("here .....");
       return new ResponseEntity<>(specialtyMapper.toDto(specialtyService.get(id)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SpecialtyResponse>> getAll () {
        List<SpecialtyResponse> specialties = specialtyService.all().stream().map(specialtyMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(specialties);
    }
}
