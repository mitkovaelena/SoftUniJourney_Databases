import entities.Customer;
import entities.Location;
import entities.Product;
import entities.Sale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("salesDB");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Customer eli = new Customer("Eli", "eli@gmail.com", "123");
        Customer eli2 = new Customer("Ivo", "ivo@gmail.com", "321");

        Location sofia = new Location("Sofia");
        Product krastavica = new Product("krastavica", 2, BigDecimal.valueOf(2.50));
        Sale sale = new Sale(krastavica,eli,sofia, new Date());

        em.persist(sale);
        Sale sale2 = new Sale(krastavica, eli2, sofia,new Date());
        Sale sale3 = new Sale(krastavica, eli, sofia,new Date());
        em.persist(sale2);
        em.persist(sale3);

        em.getTransaction().commit();
        em.close();

        em = emf.createEntityManager();

        Customer c  = em.find(Customer.class, 1);
        Customer c2  = em.find(Customer.class, 2);

        Set<Sale> sales = c.getSales();
        Set<Sale> sales2 = c2.getSales();
    }
}

