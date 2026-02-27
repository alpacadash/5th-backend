package com.spring.mvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.mvc.dto.Student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Controller
public class MVCController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void mvc() {
		System.out.println("mvc");
	}
	
	
	
	// http://localhost:8080/test11
	@GetMapping(value = "/test11")
	public ResponseEntity<Student> test11() {
		System.out.println("MVCController : test11()");
		Student student = new Student(2, "cloud", "senior");
		
		// header, body
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<Student>(student, headers, HttpStatus.OK);
	}
	
	// http://localhost:8080/test10
	@GetMapping(value = "/test10")
	public ModelAndView test10(ModelAndView mv) {
		System.out.println("MVCController : test10()");
		Student student = new Student(2, "cloud", "senior");
		
		mv.addObject("student", student);
		mv.setViewName("test");
		
		return mv;
	}
	
	
	// http://localhost:8080/test9
	@GetMapping(value = "/test9")
	@ResponseBody
	public Student test9() {
		System.out.println("MVCController : test9()");
		Student student = new Student(2, "cloud", "senior");
		return student;
	}
	
	// http://localhost:8080/test8
	@GetMapping(value = "/test8")
	public String test8() {
		System.out.println("MVCController : test8()");
		return "test";
	}
	
	// http://localhost:8080/test7?emp=7369
	// http://localhost:8080/test7/emp/7369
	@GetMapping(value = "/test7/emp/{empno}")
	public void test7(@PathVariable Integer empno) {
		System.out.println("MVCController : test7()");
		System.out.println("empno: " + empno);
	}

	// http://localhost:8080/test6
	// "{"sname": "web-mvc", "grade": "junior"}"
	@PostMapping(value = "/test6")
	public String test6(@Valid @RequestBody Student student,
						BindingResult bindingResult) {
		System.out.println("MVCController : test6()");
		System.out.println(student);
		if(bindingResult.hasErrors()) {
			return "student/form";
		}
		
		return "redirect:/students";
	}
	
	
	// http://localhost:8080/test5?sname=web-mvc&grade=junior
	@RequestMapping(value = "/test5")
	public void test5(Student student) {
		System.out.println("MVCController : test5()");
		System.out.println(student);
	}
	
	// http://localhost:8080/test4?sid=web-mvc&grade=junior
	/*
	 * Student
	 * String sid
	 * String grade
	 * 
	 */
	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public void test4(String sid, String grade) {
		System.out.println("MVCController : test4()");
		System.out.println("sid : " + sid);
		System.out.println("grade : " + grade);
		Student student = new Student(1, sid, grade);
	}

	
	// http://localhost:8080/test3?sid=web-mvc
	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public void test3(String sid) {
		System.out.println("MVCController : test3()");
		System.out.println("sid : " + sid);
	}
	
	// http://localhost:8080/test2?sid=web-mvc
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public void test2(HttpServletRequest request) {
		System.out.println("MVCController : test2()");
		
		String sid = request.getParameter("sid");
		System.out.println("sid : " + sid);
	}
	
	// http://localhost:8080/test1
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public void test1() {
		System.out.println("MVCController : test1()");
	}
	
}
