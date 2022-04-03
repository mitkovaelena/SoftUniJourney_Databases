package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class OwnedGamesCommand extends BaseCommand {

    public OwnedGamesCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        return this.getUserService().viewOwnedGames();
    }
}
