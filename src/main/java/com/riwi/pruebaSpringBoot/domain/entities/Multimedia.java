package com.riwi.pruebaSpringBoot.domain.entities;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.URL;

import com.riwi.pruebaSpringBoot.utils.enums.TypeMultimedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "multimedia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private TypeMultimedia type;
    @URL
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private Timestamp createAt;
    @Column(nullable = false)
    private boolean active; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="lessonId" , referencedColumnName = "id")
    private Lesson lesson;
}
