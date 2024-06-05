package com.riwi.pruebaSpringBoot.infraestructure.abstrac_service;

import com.riwi.pruebaSpringBoot.api.dto.request.MultimediaRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.MultimediaBasicResp;

public interface IMultimediaService extends CrudService<MultimediaRequest, MultimediaBasicResp, Long>{
    
}
