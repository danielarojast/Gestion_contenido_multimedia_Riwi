package com.riwi.pruebaSpringBoot.infraestructure.abstrac_service;

import com.riwi.pruebaSpringBoot.api.dto.request.LessonRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.LessonBasicResp;

public interface ILessonService extends CrudService<LessonRequest, LessonBasicResp, Long> {
    public LessonBasicResp disableLesson(Long id);
}
