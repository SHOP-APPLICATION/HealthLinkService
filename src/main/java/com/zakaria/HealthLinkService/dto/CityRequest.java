package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CityRequest {
    @NotNull
    @NotBlank
    @Size(min = 3)
    private String name;
    @NotNull
    private Status status;
    @NotNull
    private UUID zone;
}
