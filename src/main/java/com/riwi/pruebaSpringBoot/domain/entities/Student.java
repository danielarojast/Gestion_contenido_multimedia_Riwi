package com.riwi.pruebaSpringBoot.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String name;
    @Email
    @Column(length = 250, nullable = false)
    private String email;
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now(); 
    @Column(nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="classId" , referencedColumnName = "id")
    private ClassEntity classEntity;
}
