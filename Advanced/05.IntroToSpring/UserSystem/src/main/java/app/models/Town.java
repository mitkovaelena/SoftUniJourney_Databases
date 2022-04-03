package app.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    @OneToMany(mappedBy = "bornTown", targetEntity = User.class)
    private List<Album> bornUsers;

    @OneToMany(mappedBy = "currentTown", targetEntity = User.class)
    private List<Album> livingUsers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Album> getBornUsers() {
        return bornUsers;
    }

    public void setBornUsers(List<Album> bornUsers) {
        this.bornUsers = bornUsers;
    }

    public List<Album> getLivingUsers() {
        return livingUsers;
    }

    public void setLivingUsers(List<Album> livingUsers) {
        this.livingUsers = livingUsers;
    }
}
