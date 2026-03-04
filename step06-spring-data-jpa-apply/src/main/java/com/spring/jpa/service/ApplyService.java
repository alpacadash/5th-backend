package com.spring.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Lecture;
import com.spring.jpa.entity.Student;
import com.spring.jpa.repository.LectureRepository;
import com.spring.jpa.repository.StudentRepository;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ApplyService {
	
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	
	@Transactional
	public Object jpaApply() {
		Object result = null;
		// 비즈니스 로직 : 강의 수강하는 학생들의 이름 출력하려면?
		/*
		 [
		    "HTML",
		    "CSS",
		    "JAVASCRIPT",
		    "SERVER",
		    "SERVLET",
		    "JSP",
		    "DI",
		    "MVC",
		    "JPA"
		]
		 */

		// N + 1 해결
		// @OneToMany(fetch = FetchType.EAGER) select lecture -> select student ==> join
//		result = lectureRepository.findById(1L);
		// 해결되지 않음
		// 1000개 lec 가정
//		result = lectureRepository.findAll();
		
		// 1) 페치 조인(이너조인 -> 외부조인)
//		result = lectureRepository.findAllWithFetchJoin();
//		result = lectureRepository.findAllWithOuterFetchJoin();
		
		// 2) 그래프
		
		result = lectureRepository.findAllWithEntityGraph();
		
		return result;
	}
	
	@Transactional
	public List<Lecture> osivApply() {
		return lectureRepository.findAll();
	}
	
	@Transactional
	public Object cascadeApply() {
		
		Lecture lecture = new Lecture();
		lecture.setLname("API");

		Student stu1 = new Student();
		stu1.setSid(20244001);
		stu1.setSname("REST");
		stu1.setLecture(lecture);	
	
		Student stu2 = new Student();
		stu2.setSid(20244002);
		stu2.setSname("API");
		stu2.setLecture(lecture);
		
		lecture.getStudents().add(stu1);
		lecture.getStudents().add(stu2);
		
		// lecture와 연결이 끊김
		lecture.getStudents().remove(0);
		
		// cascade stu1, stu2 함께 저장?
		// 영속성 전이
		lectureRepository.save(lecture);
		
		return null;
	}
	
}