package com.example.RestApiDemo.service;

import java.util.List;
import java.util.Map;

import com.example.RestApiDemo.dto.AddStudentSDto;
import com.example.RestApiDemo.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudents();
	StudentDto getStudentById(Long id);
	StudentDto createStudent(AddStudentSDto addStudentSDto);
	void deleteStudentById(Long id);
	StudentDto updateStudent(Long id, AddStudentSDto addStudentSDto);
	StudentDto updatePartialStudent(Long id, Map<String, Object> update);
	
}
