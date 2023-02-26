package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ZoneRepository extends JpaRepository<Zone, UUID> {
}
