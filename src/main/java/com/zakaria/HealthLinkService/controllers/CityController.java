package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.CityRequest;
import com.zakaria.HealthLinkService.dto.CityResponse;
import com.zakaria.HealthLinkService.mappers.CityMapper;
import com.zakaria.HealthLinkService.services.CityService;
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
@RequestMapping("/api/link/cities")
@Slf4j
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityMapper cityMapper;
    @PostMapping()
    @PreAuthorize("hasAuthority('CITIES')")
    public ResponseEntity<CityResponse> save(@Valid @RequestBody CityRequest requestDTO){

        /* return ResponseEntity.ok(added);*/
        return new ResponseEntity<>(cityMapper.toDto(cityService.add(requestDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> edit(@Valid @RequestBody CityRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(cityMapper.toDto(cityService.edit(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> get(@PathVariable UUID id){
        return new ResponseEntity<>(cityMapper.toDto(cityService.get(id)), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('CITIES')")
    public ResponseEntity<List<CityResponse>> getAll () {
        List<CityResponse> cities = cityService.all().stream().map(cityMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(cities);
    }
}
