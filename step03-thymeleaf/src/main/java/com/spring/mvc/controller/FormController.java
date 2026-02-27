package com.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.mvc.dto.Student;
import com.spring.mvc.dto.Student.Grade;

@Controller
public class FormController {
	
	@GetMapping(value = "/form")
	public String form(Student student, Model model) {
		System.out.println("FormController");
		System.out.println(student);
		student.setGrade(Grade.JUNIOR);
		
		model.addAttribute("student", student);
		
		return "view-test";
	}
}
