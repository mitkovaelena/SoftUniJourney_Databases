package app.commands.impl;


import app.models.Game;
import app.services.api.GameService;
import app.services.api.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddGameCommand extends BaseCommand {
    public AddGameCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) {
        Game game = new Game();
        game.setTitle(params[0]);
        game.setPrice(new BigDecimal(params[1]));
        game.setSize(Double.parseDouble(params[2]));
        game.setTrailerId(params[3].substring(params[3].length()-11));
        game.setImageURL(params[4]);
        game.setDescription(params[5]);
        game.setReleaseDate(LocalDate.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        game.setReleaseDate(LocalDate.parse(params[6], formatter));

        return this.getGameService().addGame(game);
    }
}
