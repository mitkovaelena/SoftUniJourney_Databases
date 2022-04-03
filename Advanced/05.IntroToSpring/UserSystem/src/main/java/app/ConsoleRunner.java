package app;

import app.models.Town;
import app.models.User;
import app.services.api.AlbumService;
import app.services.api.TownService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private AlbumService albumService;
    private TownService townService;
    private UserService userService;

    @Autowired
    public ConsoleRunner(AlbumService albumService, TownService townService, UserService userService) {
        this.albumService = albumService;
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        //seedDB();

        //System.out.println(this.userService.getFullName(1L));

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String provider = reader.readLine();
        //this.userService.findUsernameAndEmailByEmailProvider(provider).forEach(s-> System.out.println(Arrays.deepToString(s) + " "));

        int numberOfDeletedUsers = this.userService.removeInactiveUsersSinceDate(new Date(2007, 7,7));
        switch (numberOfDeletedUsers){
            case 0: System.out.println("No users have been deleted");break;
            case 1: System.out.println("1 user has been deleted");break;
            default: System.out.println(numberOfDeletedUsers + "users have been deleted");break;
        }
    }

    private void seedDB() {
        Town town = new Town();
        town.setName("Sofia");
        townService.save(town);

        User u1 = new User();
        u1.setFirstName("Pesho");
        u1.setLastName("Peshov");
        u1.setUsername("pesho123");
        u1.setPassword("pEsho*123");
        u1.setEmail("pesho@gmail.com");
        u1.setLastTimeLoggedIn(new Date(2005,5,5));
        u1.setBornTown(town);
        u1.setCurrentTown(town);
        this.userService.save(u1);

        User u2 = new User();
        u2.setFirstName("Penbo");
        u2.setLastName("Penbov");
        u2.setUsername("penb");
        u2.setPassword("pEnbo*123");
        u2.setLastTimeLoggedIn(new Date(2017,5,5));
        u2.setEmail("pen@yahoo.co.uk");
        u2.setBornTown(town);
        u2.setCurrentTown(town);
        this.userService.save(u2);
    }
}
