package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

public class BuyItemCommand extends BaseCommand{
    public BuyItemCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        return this.getUserService().buyGames();
    }
}
