package app.repositories;

import app.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT CONCAT(u.firstName, ' ', u.lastName) FROM User AS u WHERE u.id = :id")
    String getFullName(@Param("id") Long id);

    @Query("SELECT u.username, u.email FROM User AS u WHERE u.email LIKE :provider")
    List<Object[]> findUsernameAndEmailByEmailProvider(@Param("provider") String provider);

    List<User> findByLastTimeLoggedInGreaterThanEqual(Date date);
}
