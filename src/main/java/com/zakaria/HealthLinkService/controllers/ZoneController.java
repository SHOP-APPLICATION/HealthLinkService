package com.zakaria.HealthLinkService.controllers;

import com.zakaria.HealthLinkService.dto.*;
import com.zakaria.HealthLinkService.dto.ZoneRequest;
import com.zakaria.HealthLinkService.dto.ZoneResponse;
import com.zakaria.HealthLinkService.mappers.ZoneMapper;
import com.zakaria.HealthLinkService.services.ZoneService;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/link/zones")
@Slf4j
public class ZoneController {
    @Autowired
    private ZoneService zoneService;
    @Autowired
    private ZoneMapper zoneMapper;
    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ZoneResponse> save(@Valid @RequestBody ZoneRequest requestDTO){

        /* return ResponseEntity.ok(added);*/
        return new ResponseEntity<>(zoneMapper.toDto(zoneService.add(requestDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ZoneResponse> edit(@Valid @RequestBody ZoneRequest requestDTO, @PathVariable UUID id){
        return new ResponseEntity<>(zoneMapper.toDto(zoneService.edit(id, requestDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        zoneService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ZONES','ADMIN')")
    public ResponseEntity<ZoneResponse> get(@PathVariable UUID id){
        return new ResponseEntity<>(zoneMapper.toDto(zoneService.get(id)), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ZONES','ADMIN')")
    public ResponseEntity<List<ZoneResponse>> getAll () {
        List<ZoneResponse> zones = zoneService.all().stream().map(zoneMapper::toDto).collect(Collectors.toList());
        return  ResponseEntity.ok(zones);
    }
}
