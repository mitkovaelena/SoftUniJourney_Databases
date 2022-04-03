package app.commands.impl;

import app.dtos.bindingDtos.RegisterUserDto;
import app.services.api.GameService;
import app.services.api.UserService;

public class RegisterUserCommand extends BaseCommand {

    public RegisterUserCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        if (!params[1].equals(params[2])) {
            return "Both passwords don't match";
        }

        try {
            RegisterUserDto user = new RegisterUserDto(params[0], params[1], params[3]);
            super.getUserService().register(user);
        } catch (Exception e) {
            return e.getMessage();
        }

        return String.format("%s was registered", params[3]);
    }
}
