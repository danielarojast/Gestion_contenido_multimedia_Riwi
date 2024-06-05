package com.riwi.pruebaSpringBoot.infraestructure.abstrac_service;

import com.riwi.pruebaSpringBoot.api.dto.request.ClassRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;

public interface IClassService extends CrudService<ClassRequest, ClassBasicResp, Long> {
    
}
