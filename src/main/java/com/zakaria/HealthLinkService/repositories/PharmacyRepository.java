package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PharmacyRepository extends JpaRepository<Pharmacy, UUID> {
}
