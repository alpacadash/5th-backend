package jpa.test;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Student;

public class PersistenceTest {
	public static void main(String[] args) {
		// EMF
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");

		// EM
		EntityManager em = emf.createEntityManager();

		// TX : begin() ~ 작업 수행 ~ commit()
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// insert
		// 20241001, "DEV"
		// 20241002, "DEVOPS"
//		Student std1 = new Student(20241001, "DEV");
//		Student std2 = new Student(20241002, "DEVOPS");
//		em.persist(std1);
//		em.persist(std2);

		// select
		// sid로 검색 20241001
//		Student foundStu1 = em.find(Student.class, 20241001);
//		System.out.println(foundStu1);

		// selectAll
		// JPQL
		// SELECT s.sname FROM Student s;
		List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();

		// SELECT * FROM student;
//		students = em.createNativeQuery("SELECT * FROM student", Student.class).getResultList();

		// update
		// DEVOPS(20241002) 이름을 IT 변경
		// 1) find -> 1차 캐시에 스냅샷 저장
//		Student foundStu2 = em.find(Student.class, 20241002);
//		System.out.println(foundStu2.getSname());

		// 2) 값을 변경
//		foundStu2.setSname("IT");
//		System.out.println(foundStu2.getSname());

		// 3) commit 시점에서 스냅샷과 현재 객체 상태비교 -> 변경사항이 있다면 update

		// delete
		// 20241002 학생 삭제
//		Student foundStu2 = em.find(Student.class, 20241002);
//		em.remove(foundStu2);

		// 특징
		// 1차 캐시
//		Student cachedStu1 = em.find(Student.class, 20241001);
//		System.out.println(cachedStu1);
//		
//		Student cachedStu2 = em.find(Student.class, 20241001);
//		System.out.println(cachedStu2);
		
		// 쓰기 지연
		// 20241003, "CLOUD"
		// 20241004, "JPA"
		
//		Student std3 = new Student(20241003, "CLOUD");
//		Student std4 = new Student(20241004, "JPA");
//		em.persist(std3);
//		em.persist(std4);
		
		// ****실행하지 말것! 문제 상황 -> 해결 방법
		// 1000건 단위로 메모리 초기화
		// 1) BATCH_SIZE
		// 2) <property name="hibernate.jdbc.batch_size" value="1000" />
		/*
		 * int BATCH_SIZE = 1000; for(int i = 0; i < 100000; i++) { Student stu = new
		 * Student(i, "학생" + i);
		 * 
		 * em.persist(stu);
		 * 
		 * if(i % BATCH_SIZE == 0) { em.flush(); // 쓰기지연 저장소 -> DB 반영 em.clear(); // 1차
		 * 캐시 초기화(메모리 확보) } }
		 */
		
		
		// 변경감지
		
		
		
		tx.commit();
	}
}
