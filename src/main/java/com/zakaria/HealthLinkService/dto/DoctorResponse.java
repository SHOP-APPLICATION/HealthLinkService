package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DoctorResponse {

    private UUID id;
    private String name;
    private AddressDto addressDto;
    private SpecialtyResponse specialtyResponse;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
}
