package com.spring.jpa.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.jpa.entity.Dept;
import com.spring.jpa.repository.DeptRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeptService {
	
	private final DeptRepository deptRepository;
	
	@Transactional
	public Object jpaTest() {
		Object result = null;
		
		// save
		Dept newDept = new Dept(99, "JPA", "SEOUL");
//		result = deptRepository.save(newDept);
		
		// findById
//		result = deptRepository.findById(99)
//								.orElseThrow(() -> new NoSuchElementException("해당 부서 없슴"));
		
		// findAll
//		result = deptRepository.findAll();
		
		// update
//		Dept foundDept = deptRepository.findById(99)
//										.orElseThrow(() -> new NoSuchElementException("해당 부서 없슴"));
//		
//		System.out.println(foundDept.getLoc());
//		foundDept.setLoc("BUSAN");
//		System.out.println(foundDept.getLoc());
		
		// delete
//		deptRepository.deleteById(99);
		
		// 쿼리메소드
		// find : loc
//		result = deptRepository.findByLoc("BOSTON");
		
//		result = deptRepository.findTop1ByLoc("BOSTON");
		
		// find : dname or loc
//		result = deptRepository.findByDnameOrLoc("ACCOUNTING", "BOSTON");
		
		// find : deptno 10 between 30
//		result = deptRepository.findByDeptnoBetween(10,30);
		
		// find : dname LIKE "O" -> %O%
//		result = deptRepository.findByDnameContains("O");
		
		// find : dname LIKE "O" -> Order By Deptno Desc
//		result = deptRepository.findByDnameContainsOrderByDeptnoDesc("O", Sort.by(Order.desc("deptno")));
		
		// find : dname LIKE "O" -> 1페이지당 2개씩 출력
//		result = deptRepository.findByDnameContains("A", PageRequest.of(0, 2));
		
		// find : deptno IN (20, 30)
		List<Integer> deptnos = Arrays.asList(20, 30);
		result = deptRepository.findByDeptnoIn(deptnos);
		
		// find : only dnames
		result = deptRepository.findAllDnames();
		
		// delete : deleteByLoc(String loc)
		// delete : 50번 이상 부서는 삭제 : deleteByDeptnoGreaterThan(Integer deptno)
		
		

		return result;
	}
}
