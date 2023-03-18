package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import io.micrometer.core.lang.Nullable;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {

    @Nullable
    private UUID id;


    @NotNull
    private UUID zone;
    @NotNull
    private UUID city;
    @NotNull
    private String Street;
    @NotNull
    private String address1;
    @NotNull
    private String zipCode;
    private Status status;
}
