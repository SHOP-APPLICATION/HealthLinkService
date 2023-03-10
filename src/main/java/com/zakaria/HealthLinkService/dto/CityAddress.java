package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CityAddress {
    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;
}
