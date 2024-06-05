package com.riwi.pruebaSpringBoot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.pruebaSpringBoot.domain.entities.Student;

@Repository
public interface StudenRepository extends JpaRepository<Student, Long>{
    
}
