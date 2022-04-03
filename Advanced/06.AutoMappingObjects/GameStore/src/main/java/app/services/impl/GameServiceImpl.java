package app.services.impl;

import app.commands.api.Executable;
import app.dtos.viewDtos.ViewGameDetailed;
import app.dtos.viewDtos.ViewGameShort;
import app.models.Game;
import app.repositories.GameRepository;
import app.services.api.GameService;
import app.services.api.UserService;
import app.utils.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    protected GameRepository dao;
    protected ModelMapper modelMapper;
    protected Session session;

    @Autowired
    public GameServiceImpl(GameRepository dao, ModelMapper modelMapper, Session session) {
        this.dao = dao;
        this.modelMapper = modelMapper;
        this.session = session;
    }

    public String addGame(Game game) {
        if (this.session.getLoggedInUser().isAdmin()) {
            this.dao.save(game);
            return String.format("Game %s added", game.getTitle());
        }
        return String.format("User %s is not an admin", this.session.getLoggedInUser().getFullName());
    }

    @Override
    public String editGame(Long id, String[] value) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        if (this.session.getLoggedInUser().isAdmin()) {
            Game game = this.dao.findOne(id);

            for (String v : value) {
                String[] tokens = v.split("=");
                Field declaredField = Game.class.getDeclaredField(tokens[0]);
                declaredField.setAccessible(true);
                if (tokens[0].equals("size")) {
                    declaredField.set(game, Double.parseDouble(tokens[1]));
                } else if (tokens[0].equals("price")) {
                    declaredField.set(game, new BigDecimal(tokens[1]));
                } else {
                    declaredField.set(game, tokens[1]);
                }
            }
            this.dao.save(game);
            return String.format("Game %s edited", game.getTitle());
        }
        return String.format("User %s is not an admin", this.session.getLoggedInUser().getFullName());

    }

    @Override
    public String deleteGame(Long id) {
        if (this.session.getLoggedInUser().isAdmin()) {
            Game game = this.dao.findOne(id);
            this.dao.delete(id);
            return game.getTitle();
        }
        return String.format("User %s is not an admin", this.session.getLoggedInUser().getFullName());

    }

    @Override
    public String viewAllGames() {
        StringBuilder sb = new StringBuilder();
        Iterable<Game> games = this.dao.findAll();
        Iterator iterator = games.iterator();
        while (iterator.hasNext()) {
            Game g = (Game) iterator.next();
            ViewGameShort viewGame = this.modelMapper.map(g, ViewGameShort.class);
            sb.append(viewGame.toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String viewGameDetails(String title) {
        Game game = this.dao.findByTitle(title);
        ViewGameDetailed viewGame = this.modelMapper.map(game, ViewGameDetailed.class);
        return viewGame.toString();
    }

    @Override
    public String addGameToSHoppingCart(String gameTitle) {
        Game game = this.dao.findByTitle(gameTitle);
        if (!this.session.getShoppingCart().stream().map(Game::getId).collect(Collectors.toSet()).contains(game.getId())) {
            this.session.getShoppingCart().add(game);
            return String.format("Game %s added to shopping cart", gameTitle);
        }
        return String.format("Game %s already in the shopping cart", gameTitle);

    }

    @Override
    public String removeGameToSHoppingCart(String gameTitle) {
        Game game = this.dao.findByTitle(gameTitle);
        for (Game g : this.session.getShoppingCart()) {
            if (g.getId().equals(game.getId())) {
                this.session.getShoppingCart().remove(g);
                return String.format("Game %s removed from shopping cart", gameTitle);
            }
        }
        return String.format("Game %s was not in the shopping cart", gameTitle);
    }
}
