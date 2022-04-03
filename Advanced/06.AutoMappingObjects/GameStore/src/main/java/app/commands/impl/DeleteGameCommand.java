package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class DeleteGameCommand extends BaseCommand {

    public DeleteGameCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        String title = this.getGameService().deleteGame(Long.parseLong(params[0]));
        return String.format("Game %s deleted", title);
    }
}
