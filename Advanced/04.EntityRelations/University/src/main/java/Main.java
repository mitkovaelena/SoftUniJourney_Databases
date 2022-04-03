import entities.api.Course;
import entities.api.Teacher;
import entities.impl.CourseImpl;
import entities.impl.StudentImpl;
import entities.impl.TeacherImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("university_system");
        EntityManager em = emf.createEntityManager();


        StudentImpl student = new StudentImpl("Pesho", "Peshov", "123123", 5.23, "Good");
        TeacherImpl teacher = new TeacherImpl("Bate", "Gosho", "213123", "b@b.bg", 21.21);
        Course course = new CourseImpl();
        course.setTeacher(teacher);
        course.setStudents(new HashSet<>(Collections.singletonList(student)));
        course.setName("Java DB");

        em.getTransaction().begin();
        em.persist(course);

        TeacherImpl t = em.find(TeacherImpl.class, 1L);
        Set<CourseImpl> courses = t.getCourses();

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
