package com.riwi.pruebaSpringBoot.api.dto.request;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.URL;

import com.riwi.pruebaSpringBoot.utils.enums.TypeMultimedia;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaRequest {
    @NotBlank(message = "El tipo de multimedia es obligarorio(IMAGEN, VIDEO)")
    private TypeMultimedia type;
    @URL
    @NotBlank(message = "Agregue la url")
    private String url;
    @NotBlank(message = "La fecha y la hora son requeridas (AAAA-MM-DD HH:MM:SS)")
    private Timestamp createAt;
    @NotBlank(message = "Indica si el estudiante esta activa con true o no activa con false")
    private boolean active; 

    @NotNull(message = "El id de la leccion es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long lessonId;
}
