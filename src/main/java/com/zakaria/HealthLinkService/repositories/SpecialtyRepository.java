package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {
}
