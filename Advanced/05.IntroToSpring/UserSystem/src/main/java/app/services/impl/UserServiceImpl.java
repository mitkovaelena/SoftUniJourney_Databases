package app.services.impl;

import app.models.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BasicService<User> implements UserService {

    @Autowired
    public UserServiceImpl(CrudRepository<User, Long> repository) {
        super(repository);
    }

    @Override
    public String getFullName(Long id) {
        UserRepository ur = (UserRepository)super.dao;
        return ur.getFullName(id);
    }

    @Override
    public List<Object[]> findUsernameAndEmailByEmailProvider(String provider) {
        UserRepository ur = (UserRepository)super.dao;
        return ur.findUsernameAndEmailByEmailProvider("%@" + provider);
    }

    @Override
    public int removeInactiveUsersSinceDate(Date date) {
        UserRepository ur = (UserRepository)super.dao;
        List<User> inactiveUsers = ur.findByLastTimeLoggedInGreaterThanEqual(date);
        int numberOfDeletedUsers = inactiveUsers.size();
        inactiveUsers.forEach(u-> super.dao.delete(u.getId()));
        return numberOfDeletedUsers;
    }
}
