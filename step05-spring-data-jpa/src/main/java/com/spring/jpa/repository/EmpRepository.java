package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.jpa.entity.Dept;
import com.spring.jpa.entity.Emp;

@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {

	List<Emp> findByJobOrDept_Deptno(String job, Integer deptno);

	List<Emp> findBySalBetween(Double sal1, Double sal2);
	
	List<Emp> findByEnameContainsOrderByEmpnoDesc(String string);

	List<Emp> findByEnameContains(String string, Sort sort);

	Page<Emp> findByEnameContains(String string, PageRequest of);

	List<Emp> findByDeptnoIn(List<Integer> empnos);
	
	@Query("select e.ename from Emp e")
	List<String> findAllEnames();

	


}
