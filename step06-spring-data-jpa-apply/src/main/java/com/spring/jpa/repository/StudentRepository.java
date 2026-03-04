package com.spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.jpa.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
