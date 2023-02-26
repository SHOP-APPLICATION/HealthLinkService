package com.zakaria.HealthLinkService.dto;

import com.zakaria.HealthLinkService.enums.Status;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {

    private UUID zone;
    private UUID city;
    private String Street;
    private String address1;
    private String zipCode;
    private Status status;
}
