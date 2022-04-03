import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student eli = new Student("Eli", new Date());
        em.persist(eli);


        Student found = em.find(Student.class, 1);
        System.out.println(found.getName());

        em.getTransaction().commit();
        em.close();
        ;
    }
}
