package app.services.api;

import app.dtos.bindingDtos.RegisterUserDto;
import app.models.Game;
import app.models.User;

import java.util.Set;

public interface UserService {
    void register(RegisterUserDto user);

    String login(String email, String password);

    String logout();

    String viewOwnedGames();

    String buyGames();
}
