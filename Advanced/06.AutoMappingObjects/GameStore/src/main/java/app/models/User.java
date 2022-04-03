package app.models;

import app.annotations.Email;
import app.annotations.Password;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Email
    private String email;

    @Password(minLength = 6,
            containsUpperCase = true,
            containsLowerCase = true,
            containsDigit = true)
    private String password;

    private String fullName;

    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_bought_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> boughtGames;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Set<Game> getBoughtGames() {
        return Collections.unmodifiableSet(boughtGames);
    }

    public void setBoughtGames(Set<Game> boughtGames) {
        this.boughtGames = boughtGames;
    }

    public boolean addBoughtGames(Set<Game> boughtGames) {
        return this.boughtGames.addAll(boughtGames);
    }

    public boolean addBoughtGames(Game boughtGames) {
        return this.boughtGames.add(boughtGames);
    }
}
