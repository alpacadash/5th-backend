package jpa.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jpa.entity.Lecture;
import jpa.entity.Student;

public class MappingTest {
	public static void method(EntityManager em) {
		// insert
		// Tech 강좌
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);

		// 20242001, "jpa", Tech 강좌
//		Student stu = new Student();
//		stu.setLid(1L);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);

		// select
		// 학생이 참가하고 있는 수업의 정보 출력?
		// 1) 학생 -> lid
		// 2) 수업 반환
//		Long lid = em.find(Student.class, 20242001).getLid();
//		Lecture foundLec = em.find(Lecture.class, lid);
//		System.out.println(foundLec);
		
		// 해결 @JoinColumn + @ManyToOne -> create
		// FK 반영 배용을 확인 후 -> none
//		Lecture lec = new Lecture();
//		lec.setLname("Tech");
//		em.persist(lec);
//
//		Student stu = new Student();
//		stu.setLecture(lec);
//		stu.setSid(20242001);
//		stu.setSname("jpa");
//		em.persist(stu);
		
		Lecture foundLecture = em.find(Student.class, 20242001).getLecture();
		System.out.println(foundLecture);
		
		
		
		// 강좌에 참여하고 있는 학생의 정보 출력?

	}

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			method(em);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}

}
