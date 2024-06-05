package com.riwi.pruebaSpringBoot.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.pruebaSpringBoot.domain.entities.Student;

@Repository
public interface StudenRepository extends JpaRepository<Student, Long>{
    //Metodo que busca Estudiante por nombre
    List<Student>findByName(String name);
}
