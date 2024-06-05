package com.riwi.pruebaSpringBoot.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "lesson")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, nullable = false)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now(); 
    @Column(nullable = false)
    private boolean active; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="classId" , referencedColumnName = "id")
    private ClassEntity classEntity;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL,orphanRemoval = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Multimedia> multimedias;
}
