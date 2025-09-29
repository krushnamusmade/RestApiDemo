package com.example.RestApiDemo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentSDto {
	
	@NotBlank(message = "name should not blank")
	@Size(min = 3 , max = 30 , message = "size should be 3 to 30 range")
	String name;
	
	@Email
	@NotBlank(message = "email should not blank")
	String email;

}
