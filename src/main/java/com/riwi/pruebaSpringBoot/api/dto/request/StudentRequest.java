package com.riwi.pruebaSpringBoot.api.dto.request;

import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    @NotBlank(message = "El nombre del estudiante es necesario")
    @Size(min = 4, max = 50)
    private String name;
    @Email
    @NotBlank(message = "El email es requerido")
    private String email;
    @NotBlank(message = "La fecha y la hora son requeridas (AAAA-MM-DD HH:MM:SS)")
    private Timestamp createAt;
    @NotBlank(message = "Indica si el estudiante esta activa con true o no activa con false")
    private boolean active; 

    @NotNull(message = "El id de la clase es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long classId;
}
