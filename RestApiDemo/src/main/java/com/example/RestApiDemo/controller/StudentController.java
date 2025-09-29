package com.example.RestApiDemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestApiDemo.dto.AddStudentSDto;
import com.example.RestApiDemo.dto.StudentDto;
import com.example.RestApiDemo.service.StudentService;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
    
	
	

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getStudent() {
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<StudentDto> getStudentbyId(@PathVariable Long id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	} 
	
	@PostMapping("/student")
	public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid AddStudentSDto addStudentSDto ){
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(addStudentSDto));
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<StudentDto> updateStuent(@PathVariable Long id, @RequestBody AddStudentSDto addStudentSDto){
		return ResponseEntity.ok(studentService.updateStudent(id,addStudentSDto));
	}
	
	@PatchMapping("/student/{id}")
	public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id,@RequestBody Map<String, Object> update){
		return ResponseEntity.ok(studentService.updatePartialStudent(id,update));
	}
	
	
	
	

}
