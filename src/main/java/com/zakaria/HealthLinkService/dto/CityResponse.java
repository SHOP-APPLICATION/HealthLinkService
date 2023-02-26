package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CityResponse {
    private UUID id;
    private String name;
    private ZoneResponse zone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
}
