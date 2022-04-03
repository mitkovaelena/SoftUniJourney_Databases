package app.services.impl;

import app.dtos.bindingDtos.LoggedInUserDto;
import app.dtos.bindingDtos.RegisterUserDto;
import app.dtos.viewDtos.ViewGameShort;
import app.models.Game;
import app.models.User;
import app.repositories.UserRepository;
import app.services.api.UserService;
import app.utils.Session;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    protected UserRepository dao;
    protected ModelMapper modelMapper;
    protected Session session;

    @Autowired
    public UserServiceImpl(UserRepository dao, ModelMapper modelMapper, Session session) {
        this.dao = dao;
        this.modelMapper = modelMapper;
        this.session = session;
    }

    @Override
    public void register(RegisterUserDto registerUserDto) {
        User concreteUser = this.modelMapper.map(registerUserDto, User.class);
        if (!this.dao.findAll().iterator().hasNext()) {
            concreteUser.setAdmin(true);
        }
        this.dao.save(concreteUser);
    }

    @Override
    public String login(String email, String password) {
        User user = this.dao.findByEmailAndPassword(email, password);
        if (user == null) {
            return "Wrong email/password";
        }

        LoggedInUserDto loggedInUserDto = this.modelMapper.map(user, LoggedInUserDto.class);
        this.session.setLoggedInUser(loggedInUserDto);
        return String.format("%s logged in", loggedInUserDto.getFullName());
    }

    @Override
    public String logout() {
        String name = this.session.getLoggedInUser().getFullName();
        this.session.setLoggedInUser(null);
        return name;
    }

    @Override
    public String viewOwnedGames() {
        StringBuilder sb = new StringBuilder();
        Set<Game> games = this.dao.findOne(this.session.getLoggedInUser().getId()).getBoughtGames();
        for (Game game : games) {
            ViewGameShort viewGame = this.modelMapper.map(game, ViewGameShort.class);
            sb.append(viewGame.toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String buyGames() {
        StringBuilder sb = new StringBuilder();
        User user = this.dao.findOne(this.session.getLoggedInUser().getId());
        for (Game game : this.session.getShoppingCart()) {
            if (!user.getBoughtGames().stream().map(Game::getId).collect(Collectors.toSet()).contains(game.getId())) {
                user.addBoughtGames(game);
                sb.append("\n  - " + game.getTitle());
            }
        }
        this.dao.save(user);
        return String.format("Bought games:" + sb.toString());
    }

}
