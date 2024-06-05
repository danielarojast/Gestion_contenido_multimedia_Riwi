package com.riwi.pruebaSpringBoot.infraestructure.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.pruebaSpringBoot.api.dto.request.LessonRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;
import com.riwi.pruebaSpringBoot.api.dto.response.LessonBasicResp;
import com.riwi.pruebaSpringBoot.api.dto.response.MultimediaBasicResp;
import com.riwi.pruebaSpringBoot.domain.entities.ClassEntity;
import com.riwi.pruebaSpringBoot.domain.entities.Lesson;
import com.riwi.pruebaSpringBoot.domain.entities.Multimedia;
import com.riwi.pruebaSpringBoot.domain.entities.Student;
import com.riwi.pruebaSpringBoot.domain.repositories.ClassRepository;
import com.riwi.pruebaSpringBoot.domain.repositories.LessonRepository;
import com.riwi.pruebaSpringBoot.domain.repositories.MultimediaRepository;
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
    @Autowired
    private final MultimediaRepository multimediaRepository;

    @Override
    public LessonBasicResp create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);

        this.lessonRepository.save(lesson);
        request.getMultimedia().stream().forEach(multimedia ->{
            Multimedia newMultimedia= Multimedia.builder()
                .type(multimedia.getType())
                .url(multimedia.getUrl())
                .active(multimedia.isActive())
                .lesson(lesson)
                .build();

                this.multimediaRepository.save(newMultimedia);
        });
        
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
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

    @Override
    public LessonBasicResp disableLesson(Long id) {
        Lesson entity = this.find(id);
        entity.setActive(false);

        return entityToResponse(this.lessonRepository.save(entity));
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

        List<MultimediaBasicResp> multimedia  = entity.getMultimedias()
            .stream()
            .map(this::entityToResponseMultimedia)
            .collect(Collectors.toList());

        return LessonBasicResp.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .createAt(entity.getCreateAt())
                .active(entity.isActive())
                .multimedia(multimedia)
                .classEntity(classEntity)
                .build();

    }

    private MultimediaBasicResp entityToResponseMultimedia(Multimedia entity){

        LessonBasicResp lesson = new LessonBasicResp();
        BeanUtils.copyProperties(entity.getLesson(), lesson);

        return MultimediaBasicResp.builder()
                    .id(entity.getId())
                    .type(entity.getType())
                    .url(entity.getUrl())
                    .createAt(entity.getCreateAt())
                    .active(entity.isActive())
                    .lesson(lesson)
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
