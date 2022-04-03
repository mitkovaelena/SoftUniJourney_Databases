package app.commands.impl;

import app.commands.api.Executable;
import app.services.api.GameService;
import app.services.api.UserService;

public abstract class BaseCommand implements Executable {

    private UserService userService;

    private GameService gameService;

    protected BaseCommand(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    protected UserService getUserService() {
        return userService;
    }

    protected GameService getGameService() {
        return gameService;
    }
}
