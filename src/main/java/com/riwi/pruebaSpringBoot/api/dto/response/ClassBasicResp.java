package com.riwi.pruebaSpringBoot.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClassBasicResp {
    private Long id; 
    private String name;
    private String description;
    private LocalDateTime createAt;
    private boolean active; 
}
