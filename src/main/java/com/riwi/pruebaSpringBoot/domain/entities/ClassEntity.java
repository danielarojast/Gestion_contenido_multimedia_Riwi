package com.riwi.pruebaSpringBoot.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String name;
    @Lob
    @Column(nullable = false) 
    private String description;
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now(); 
    @Column(nullable = false)
    private boolean active; 

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL,orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Student> students;

    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL,orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Lesson> lessons;

}
