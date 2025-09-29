package com.example.RestApiDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.RestApiDemo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
