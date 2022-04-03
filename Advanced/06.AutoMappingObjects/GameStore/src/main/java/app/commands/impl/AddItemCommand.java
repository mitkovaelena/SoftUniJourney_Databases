package app.commands.impl;


import app.models.Game;
import app.services.api.GameService;
import app.services.api.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddItemCommand extends BaseCommand {
    public AddItemCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        return this.getGameService().addGameToSHoppingCart(params[0]);
    }
}
