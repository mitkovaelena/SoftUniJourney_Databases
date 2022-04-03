import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        EntityManagerFactory emf = Persistence.
                createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        /*#1
        List<Town> townList = em.createQuery(
                "select t from Town as t where length(t.name) > 5").getResultList();
        for( Town t : townList){
            em.detach(t);
            t.setName(t.getName().toLowerCase());
            em.merge(t);
        }

       #2
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        List<Town> rs = em.createQuery(
                "select e from Employee as e " +
                   "where CONCAT(e.firstName, ' ', e.lastName) = ?")
                .setParameter(0, name)
                .getResultList();

        System.out.println(rs.isEmpty()? "No" : "Yes");

        #3
        List<Employee> rs = em.createQuery(
                "select e from Employee as e " +
                   "where e.salary > 50000")
                .getResultList();
        for( Employee e : rs){
            System.out.println(e.getFirstName());
        }

        #4
        List<Employee> rs = em.createQuery(
                "select e from Employee as e " +
                   "where e.department.name = 'Research and Development' " +
                   "order by e.salary, e.id")
                .getResultList();
        for( Employee e : rs){
            System.out.printf("%s %s from %s - $%.2f%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getDepartment().getName(),
                    e.getSalary());
        }

        #5
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        Address address = new Address();
        address.setText("Vitoshka 15");
        em.persist(address);

        Employee employee = (Employee) em.createQuery(
                "select e from Employee as e " +
                   "where e.lastName = ?")
                .setParameter(0, name)
                .setMaxResults(1)
                .getSingleResult();

        employee.setAddress(address);

        #6
        List<Address> rs = em.createQuery(
                "select a from Address as a " +
                   "order by a.employees.size desc, a.town.id")
                .setMaxResults(10)
                .getResultList();
        for( Address a : rs){
            System.out.printf("%s, %s - %d employees%n",
                    a.getText(),
                    a.getTown().getName(),
                    a.getEmployees().size());
        }

        #7
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        Employee employee = (Employee) em.createQuery(
                "select e from Employee as e where e.id = ?")
                .setParameter(0, id)
                .getSingleResult();
        System.out.printf("%s %s - %s%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartment().getName());

        employee.getProjects().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.println(p.getName()));

        #8
        List<Project> rs = em.createQuery(
                "select p from Project as p " +
                   "order by p.startDate DESC")
                .setMaxResults(10)
                .getResultList();
        rs.stream().sorted(Comparator.comparing(Project::getName)).
                forEach(p-> System.out.printf(
                        "Project name: %s\n" +
                        " \tProject Description: %s\n" +
                        " \tProject Start Date: %s\n" +
                        " \tProject End Date: %s%n",
                        p.getName(), p.getDescription(),
                        p.getStartDate().toString(),
                        p.getEndDate().toString()));

        #9
        List<Employee> rs = em.createQuery(
                "select e from Employee as e " +
                        "where e.department.name = 'Engineering' " +
                        "OR e.department.name = 'Tool Design' " +
                        "OR e.department.name = 'Marketing' " +
                        "OR e.department.name = 'Information Services'")
                .getResultList();

        for( Employee e : rs){
            e.setSalary(e.getSalary().multiply(new BigDecimal("1.12")));
            System.out.printf("%s %s ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getSalary());
        }

        #10
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        List<Address> addresses = em.createQuery(
                "select a from Address as a " +
                   "where a.town.name = ?")
                .setParameter(0, name)
                .getResultList();
        for (Address address : addresses) {
            for (Employee employee : address.getEmployees()) {
                employee.setAddress(null);
            }
            em.remove(address);
        }
        Town town = (Town) em.createQuery(
                "select t from Town as t where t.name = ?")
                .setParameter(0, name)
                .getSingleResult();
        em.remove(town);
        System.out.println(addresses.size() + " address\\es in " + town.getName() + " deleted");

        #11
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String pattern = reader.readLine();
        List<Employee> rs = em.createQuery(
                "select e from Employee as e where e.firstName LIKE ?")
                .setParameter(0, pattern + "%")
                .getResultList();

        for( Employee e : rs){
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    e.getFirstName(),
                    e.getLastName(),
                    e.getJobTitle(),
                    e.getSalary());
        }

        #12
        List<Object[]> rs = em.createQuery(
                "select e.department.name, max(e.salary) " +
                   "from Employee as e group by e.department.name " +
                   "having max(e.salary) not between 30000 AND 70000")
                 .getResultList();

        for( Object[] o : rs) {
            System.out.printf("%s - %.2f%n", o[0], o[1]);
        }*/

        Department d = em.find(Department.class,7);
        Set<Employee>  departments = d.getEmployees();

        em.getTransaction().commit();
        em.close();
    }
}
