package com.riwi.pruebaSpringBoot.infraestructure.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.pruebaSpringBoot.api.dto.request.StudentRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;
import com.riwi.pruebaSpringBoot.api.dto.response.StudenBasicResp;
import com.riwi.pruebaSpringBoot.domain.entities.ClassEntity;
import com.riwi.pruebaSpringBoot.domain.entities.Student;
import com.riwi.pruebaSpringBoot.domain.repositories.ClassRepository;
import com.riwi.pruebaSpringBoot.domain.repositories.StudenRepository;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.IStudentService;
import com.riwi.pruebaSpringBoot.utils.exception.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService{
    @Autowired
    private final StudenRepository studenRepository;
    @Autowired
    private final ClassRepository classRepository;
    
    @Override
    public StudenBasicResp create(StudentRequest request) {
        Student student = this.requestToEntity(request);
        
        return this.entityToResponse(this.studenRepository.save(student));
    }

    @Override
    public StudenBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public StudenBasicResp update(StudentRequest request, Long id) {
        Student entity = this.find(id);
        entity = requestToEntity(request);
        entity.setId(id);

        return entityToResponse(this.studenRepository.save(entity));
    }

    @Override
    public StudenBasicResp disableStudent( Long id) {
        Student entity = this.find(id);
        entity.setActive(false);

        return entityToResponse(this.studenRepository.save(entity));
    }

    @Override
    public void dalete(Long id) {
        /* */
        throw new UnsupportedOperationException("Unimplemented method 'dalete'");
    }

    @Override
    public Page<StudenBasicResp> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagination= PageRequest.of(page, size);

        return this.studenRepository.findAll(pagination)
                            .map(this::entityToResponse);

    }

    @Override
    public List<StudenBasicResp> findByName(String name) {
        return this.studenRepository.findByName(name)
        .stream()
        .map(this::entityToResponse)
        .collect(Collectors.toList());
    }

    /*************************************************/

    private StudenBasicResp entityToResponse(Student entity) {

        ClassBasicResp classEntity = ClassBasicResp.builder()
            .id(entity.getClassEntity().getId())
            .name(entity.getClassEntity().getName())
            .description(entity.getClassEntity().getDescription())
            .createAt(entity.getClassEntity().getCreateAt())
            .active(entity.getClassEntity().isActive())
            .build();

        return StudenBasicResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .createAt(entity.getCreateAt())
                .active(entity.isActive())
                .classEntity(classEntity)
                .build();

    }

    private Student requestToEntity(StudentRequest request) {

        return Student.builder()
            .name(request.getName())
            .email(request.getEmail())
            .active(request.isActive())
            .classEntity(this.findClassEntity(request.getClassId()))
            .build();
    }

    private Student find(Long id) {
        return this.studenRepository.findById(id)
            .orElseThrow(()-> new BadRequestException("No hay studiantes con el id suministrado"));
    }

    private ClassEntity findClassEntity(Long id){
        return this.classRepository.findById(id)
                                .orElseThrow(() -> new BadRequestException("No hay clase con el id suministrado"));
    }

    
    
}
