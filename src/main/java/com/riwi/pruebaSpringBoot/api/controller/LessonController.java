package com.riwi.pruebaSpringBoot.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.pruebaSpringBoot.api.dto.request.LessonRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.LessonBasicResp;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.ILessonService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/lesson")
public class LessonController {
    
    private final ILessonService lessonService;
    
    /***** INSERT ****/
    
    @PostMapping
    public ResponseEntity<LessonBasicResp> insert(
            @Validated @RequestBody LessonRequest request) {
        return ResponseEntity.ok(this.lessonService.create(request));
    }

    /***** GET ALL ****/
    
    @GetMapping
    public ResponseEntity<Page<LessonBasicResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
    
        return ResponseEntity.ok(this.lessonService.getAll(page - 1, size));
    }
}
