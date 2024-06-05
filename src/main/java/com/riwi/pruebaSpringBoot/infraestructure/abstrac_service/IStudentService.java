package com.riwi.pruebaSpringBoot.infraestructure.abstrac_service;

import java.util.List;

import com.riwi.pruebaSpringBoot.api.dto.request.StudentRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.StudenBasicResp;

public interface IStudentService extends CrudService<StudentRequest, StudenBasicResp, Long> {
    List<StudenBasicResp>findByName(String name);
    public StudenBasicResp disableStudent(Long id);
}
