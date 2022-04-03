package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class LoginUserCommand extends BaseCommand {

    public LoginUserCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
            return super.getUserService().login(params[0], params[1]);
    }
}
