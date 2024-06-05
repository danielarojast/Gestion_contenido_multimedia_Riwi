package com.riwi.pruebaSpringBoot.api.dto.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LessonBasicResp {
    private Long id;
    private String title;
    private String content;
    private Timestamp createAt;
    private boolean active; 

    private ClassBasicResp classEntity;
}
