package app.services;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import app.repositories.UserRepository;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if ((user.getId() != null && userRepository.findOne(user.getId()) != null)
        || (user.getUsername() != null && userRepository.findByUsername(user.getUsername()) != null)){
            throw new IllegalArgumentException("Duplicate users");
        }
        userRepository.save(user);
    }
}
