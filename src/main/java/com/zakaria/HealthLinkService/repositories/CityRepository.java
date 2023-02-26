package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
}
