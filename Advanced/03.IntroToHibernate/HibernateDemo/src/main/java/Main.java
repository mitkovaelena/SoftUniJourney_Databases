import com.mysql.cj.jdbc.util.TimeUtil;
import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.mysql.jdbc.*;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student eli = new Student();
        Student ivo = new Student("Ivaylo", new Date());
        eli.setName("Elena");
        eli.setRegistrationDate(new Date());
        session.persist(eli);
        session.persist(ivo);
        eli.setName("Eli");
        System.out.println(session.get(Student.class, 1).getName());

        List<Student> studentList =
                session.createQuery("FROM Student").list();
        for (Student student : studentList) {
            System.out.println(student.getId());
        }

        List<Student> students =
                session.createCriteria(Student.class)
                        .add(Restrictions.like("name", 	"I%")).list();
        for(Student s : students){
            System.out.println(s.getName());
        }

        session.getTransaction().commit();
        session.close();
    }
}
