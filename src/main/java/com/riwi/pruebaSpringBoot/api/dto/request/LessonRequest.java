package com.riwi.pruebaSpringBoot.api.dto.request;


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
public class LessonRequest {
    @NotBlank(message = "El titulo es necesario")
    @Size(min = 4, max = 50)
    private String title;
    @NotBlank(message = "El contenido es requerido")
    private String content;
    @NotNull(message = "Indica si la leccion esta activa con true o no activa con false")
    private boolean active; 

    @NotNull(message = "El id de la clase es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor a cero ")
    private Long classId;
}
