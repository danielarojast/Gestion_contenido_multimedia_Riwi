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
public class StudenBasicResp {
    private String name;
    private String email;
    private Timestamp createAt;
    private boolean active;
    private ClassBasicResp classEntity;
}
