package com.zakaria.HealthLinkService.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zakaria.HealthLinkService.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    private UUID id;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="specialty_id" , nullable = false)
    private Specialty specialty;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="address_id" , nullable = false)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
    @Column(nullable = false, updatable = true)
    @UpdateTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updatedAt;
    @Column(nullable = true)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deletedAt;
}
