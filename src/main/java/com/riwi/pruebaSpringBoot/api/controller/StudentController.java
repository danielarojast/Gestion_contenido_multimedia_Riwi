package com.riwi.pruebaSpringBoot.api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.pruebaSpringBoot.api.dto.request.StudentRequest;
import com.riwi.pruebaSpringBoot.api.dto.response.StudenBasicResp;
import com.riwi.pruebaSpringBoot.infraestructure.abstrac_service.IStudentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {

    private final IStudentService studentService;
    
    /***** INSERT ****/
    
    @PostMapping
    public ResponseEntity<StudenBasicResp> insert(
            @Validated @RequestBody StudentRequest request) {
        return ResponseEntity.ok(this.studentService.create(request));
    }

    /***** GET ALL ****/
    
    @GetMapping
    public ResponseEntity<Page<StudenBasicResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
    
        return ResponseEntity.ok(this.studentService.getAll(page - 1, size));
    }


    /***** GET BY ID ****/

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudenBasicResp> get(
            @PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.get(id));
    }
    
    /***** UPDATE ****/
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<StudenBasicResp> update(
            @Validated @RequestBody StudentRequest request,
            @PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.update(request, id));
    }

    /****Disable Student******/
    @PatchMapping(path= "/{id}/disable")
    public ResponseEntity<StudenBasicResp> disableStudent(
    
            @PathVariable Long id) {
        return ResponseEntity.ok(this.studentService.disableStudent(id));
    }

    /****GET BY NAME *****/
    @GetMapping(path = "/name/{name}")
    public ResponseEntity <List<StudenBasicResp>> getName(
            @PathVariable String name) {

        return ResponseEntity.ok(this.studentService.findByName(String.valueOf(name)));
    }

}