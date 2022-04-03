package app.services.api;

import app.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface UserService extends ServiceInterface<User> {

    String getFullName(Long id);

    List<Object[]> findUsernameAndEmailByEmailProvider(String provider);

    int removeInactiveUsersSinceDate(Date date);
}
