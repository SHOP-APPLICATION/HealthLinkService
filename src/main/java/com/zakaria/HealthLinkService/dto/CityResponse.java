package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
public class CityResponse {
    private UUID id;
    private String name;
    private ZoneResponse zone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
}
