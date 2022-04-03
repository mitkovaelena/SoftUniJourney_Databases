package app.repositories;

import app.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
}
