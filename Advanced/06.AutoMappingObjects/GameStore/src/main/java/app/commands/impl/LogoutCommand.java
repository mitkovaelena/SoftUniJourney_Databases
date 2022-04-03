package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class LogoutCommand extends BaseCommand {
    public LogoutCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        String name = super.getUserService().logout();
        return String.format("%s logged out", name);
    }
}
