package com.riwi.pruebaSpringBoot.api.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
    @NotBlank(message = "El nombre de la clase es necesario")
    @Size(min = 4, max = 50)
    private String name; 
    private String description;
    @NotBlank(message = "La fecha y la hora son requeridas (AAAA-MM-DD HH:MM:SS)")
    private Timestamp createAt;
    @NotBlank(message = "Indica si la classe esta activa con true o no activa con false")
    private boolean active; 


}
