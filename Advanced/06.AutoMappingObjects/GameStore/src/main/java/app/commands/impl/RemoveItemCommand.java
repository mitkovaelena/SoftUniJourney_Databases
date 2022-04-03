package app.commands.impl;


import app.services.api.GameService;
import app.services.api.UserService;

public class RemoveItemCommand extends BaseCommand {
    public RemoveItemCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        return this.getGameService().removeGameToSHoppingCart(params[0]);
    }
}
