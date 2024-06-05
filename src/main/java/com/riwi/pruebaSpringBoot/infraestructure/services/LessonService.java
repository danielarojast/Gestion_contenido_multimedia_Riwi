package com.riwi.pruebaSpringBoot.infraestructure.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.pruebaSpringBoot.api.dto.request.LessonRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;
import com.riwi.pruebaSpringBoot.api.dto.response.LessonBasicResp;
import com.riwi.pruebaSpringBoot.domain.entities.ClassEntity;
import com.riwi.pruebaSpringBoot.domain.entities.Lesson;
import com.riwi.pruebaSpringBoot.domain.repositories.ClassRepository;
import com.riwi.pruebaSpringBoot.domain.repositories.LessonRepository;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.ILessonService;
import com.riwi.pruebaSpringBoot.utils.exception.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService{
    
     @Autowired
    private final LessonRepository lessonRepository;
    @Autowired
    private final ClassRepository classRepository;

    @Override
    public LessonBasicResp create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);
        
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonBasicResp get(Long id) {
        /* */
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public LessonBasicResp update(LessonRequest request, Long id) {
        /* */
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void dalete(Long id) {
       /* */
        throw new UnsupportedOperationException("Unimplemented method 'dalete'");
    }

    @Override
    public Page<LessonBasicResp> getAll(int page, int size) {
         if (page < 0) page = 0;

        PageRequest pagination= PageRequest.of(page, size);

        return this.lessonRepository.findAll(pagination)
                            .map(this::entityToResponse);
    }

    /*************************************************/

    private LessonBasicResp entityToResponse(Lesson entity) {

        ClassBasicResp classEntity = ClassBasicResp.builder()
            .id(entity.getClassEntity().getId())
            .name(entity.getClassEntity().getName())
            .description(entity.getClassEntity().getDescription())
            .createAt(entity.getClassEntity().getCreateAt())
            .active(entity.getClassEntity().isActive())
            .build();

        return LessonBasicResp.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createAt(entity.getCreateAt())
                .active(entity.isActive())
                .classEntity(classEntity)
                .build();

    }

    private Lesson requestToEntity(LessonRequest request) {

        return Lesson.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .active(request.isActive())
            .classEntity(this.findClassEntity(request.getClassId()))
            .build();
    }

    private Lesson find(Long id) {
        return this.lessonRepository.findById(id)
            .orElseThrow(()-> new BadRequestException("No hay leccion con el id suministrado"));
    }

    private ClassEntity findClassEntity(Long id){
        return this.classRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay clase con el id suministrado"));
    }
    
}
