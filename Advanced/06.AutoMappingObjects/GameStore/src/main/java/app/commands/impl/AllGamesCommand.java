package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class AllGamesCommand extends BaseCommand {

    public AllGamesCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        return this.getGameService().viewAllGames();
    }
}
