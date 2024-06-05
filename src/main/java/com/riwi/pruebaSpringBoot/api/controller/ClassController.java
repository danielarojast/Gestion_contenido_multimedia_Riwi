package com.riwi.pruebaSpringBoot.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.pruebaSpringBoot.api.dto.request.ClassRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.ClassBasicResp;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.IClassService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/class")
public class ClassController {
    private final IClassService classService; 
    
    /****INSERT*** */
    @PostMapping
    public ResponseEntity<ClassBasicResp> insert(
            @Validated @RequestBody ClassRequest request) {
        return ResponseEntity.ok(this.classService.create(request));
    }

    /***** GET ALL ****/
    @GetMapping
    public ResponseEntity<Page<ClassBasicResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(this.classService.getAll(page - 1, size));
    }

    /***** GET BY ID****/
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassBasicResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.classService.get(id));
    }

}
