package com.riwi.pruebaSpringBoot.infraestructure.abstrac_service;

import com.riwi.pruebaSpringBoot.api.dto.request.StudentRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.StudenBasicResp;

public interface IStudentService extends CrudService<StudentRequest, StudenBasicResp, Long> {
    
}
