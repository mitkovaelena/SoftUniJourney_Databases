import entities.Wizard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gringotts");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Wizard wizard = new Wizard("Nikolova", 21);

        em.persist(wizard);

        em.getTransaction().commit();
        em.close();
        ;
    }
}
