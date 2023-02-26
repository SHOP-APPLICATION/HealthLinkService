package com.zakaria.HealthLinkService.repositories;

import com.zakaria.HealthLinkService.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
