package com.riwi.pruebaSpringBoot.api.dto.response;

import java.time.LocalDateTime;

import com.riwi.pruebaSpringBoot.utils.enums.TypeMultimedia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaBasicResp {
    private Long id;
    private TypeMultimedia type;
    private String url;
    private LocalDateTime createAt;
    private boolean active; 

    private LessonBasicResp lesson;
}
