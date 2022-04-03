package app.services.api;

import app.models.Game;

public interface GameService {
    String addGame(Game game);

    String editGame(Long id, String[] value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException;

    String deleteGame(Long id);

    String viewAllGames();

    String viewGameDetails(String title);

    String addGameToSHoppingCart(String gameTitle);

    String removeGameToSHoppingCart(String gameTitle);
}
