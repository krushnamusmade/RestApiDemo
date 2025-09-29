package com.example.RestApiDemo.service.imp;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.RestApiDemo.dto.AddStudentSDto;
import com.example.RestApiDemo.dto.StudentDto;
import com.example.RestApiDemo.entity.Student;
import com.example.RestApiDemo.repository.StudentRepository;
import com.example.RestApiDemo.service.StudentService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	
	@Override
	public List<StudentDto> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		List<StudentDto> studentDtos = students
				.stream()
				.map(student ->new StudentDto(student.getId(),student.getName(),student.getEmail()))
				//also return .map(student ->modelMapper.map(student, StudentDto.class))
				.toList();
		return studentDtos;
	}

	@Override
	public StudentDto getStudentById(Long id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(" Id not found"));
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto createStudent(AddStudentSDto addStudentSDto) {
		// TODO Auto-generated method stub
		Student newStudent = modelMapper.map(addStudentSDto, Student.class);
		Student student = studentRepository.save(newStudent);
		return modelMapper.map(student , StudentDto.class);
	}

	@Override
	public void deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		if(!studentRepository.existsById(id)) {
			throw new IllegalArgumentException("student id not found : "+id);
		}
		 studentRepository.deleteById(id);
	}

	@Override
	public StudentDto updateStudent(Long id, AddStudentSDto addStudentSDto) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id not found in database : "+id));
		modelMapper.map(addStudentSDto,student);
		
		student = studentRepository.save(student); 
		
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto updatePartialStudent(Long id, Map<String, Object> update) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Id not found in database : "+id));
		
		update.forEach((field,value) ->{
			switch(field) {
			case "name" : 
				student.setName((String) value);
				break;
			case "email" : 
				student.setEmail((String) value);
				break;
			default :
				throw new IllegalArgumentException("Filed is not supported");
			
			}
		});
		Student student2 = studentRepository.save(student);
		return modelMapper.map(student2, StudentDto.class);
	}

}

