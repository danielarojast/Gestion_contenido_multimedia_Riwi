package com.riwi.pruebaSpringBoot.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.pruebaSpringBoot.api.dto.request.ClassRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;
import com.riwi.pruebaSpringBoot.domain.entities.ClassEntity;
import com.riwi.pruebaSpringBoot.domain.repositories.ClassRepository;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.IClassService;
import com.riwi.pruebaSpringBoot.utils.exception.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassService implements IClassService{
    
    @Autowired
    private final ClassRepository classRepository;

    @Override
    public ClassBasicResp create(ClassRequest request) {
        ClassEntity classEntity = this.requestToEntity(request);
        System.out.println(classEntity);
        return this.entityToResponse(this.classRepository.save(classEntity));
    }

    @Override
    public ClassBasicResp get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public ClassBasicResp update(ClassRequest request, Long id) {
        /* */
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void dalete(Long id) {
         /* */
        throw new UnsupportedOperationException("Unimplemented method 'dalete'");
    }

    @Override
    public Page<ClassBasicResp> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagination= PageRequest.of(page, size);

        return this.classRepository.findAll(pagination)
                            .map(this::entityToResponse);
    }

    /*********** Metodos adicionales para el proceso *************************/

    private ClassBasicResp entityToResponse(ClassEntity entity) {

    
        return ClassBasicResp.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createAt(entity.getCreateAt())
                .active(entity.isActive())
                .build();
    }

    private ClassEntity requestToEntity(ClassRequest request) {

        return ClassEntity.builder()
            .name(request.getName())
            .description(request.getDescription())
            .active(request.isActive())
            .build();
    }

    private ClassEntity find(Long id) {
        return this.classRepository.findById(id)
            .orElseThrow(()-> new BadRequestException("No hay clases con el id suministrado"));
    }
}
