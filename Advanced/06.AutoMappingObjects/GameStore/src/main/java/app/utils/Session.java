package app.utils;

import app.dtos.bindingDtos.LoggedInUserDto;
import app.models.Game;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class Session {
    private LoggedInUserDto loggedInUser;
    private Set<Game> shoppingCart;

    public LoggedInUserDto getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(LoggedInUserDto newUser) {
        loggedInUser = newUser;
        this.setShoppingCart(new HashSet<>());
    }

    public Set<Game> getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(Set<Game> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
