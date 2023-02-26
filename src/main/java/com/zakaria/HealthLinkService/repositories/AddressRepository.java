package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
