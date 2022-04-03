package app;

import app.commands.api.Executable;
import app.interpreters.CommandInterpreter;
import app.services.api.GameService;
import app.services.api.UserService;
import app.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private GameService gameService;
    private Session session;

    @Autowired
    public ConsoleRunner(UserService userService, GameService gameService, Session session) {
        this.userService = userService;
        this.gameService = gameService;
        this.session= session;
    }

    @Override
    public void run(String... strings) throws Exception {
        CommandInterpreter commandInterpreter = new CommandInterpreter(this.userService, this.gameService, this.session);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();

            if ("end".equals(line.toLowerCase())) {
                break;
            }
            if(line.trim().equals("")){
                continue;
            }

            String[] tokens = line.split("\\|");
            String command = tokens[0];

            String[] newParams = new String[tokens.length - 1];

            if (tokens.length > 1) {
                newParams = Arrays.copyOfRange(tokens, 1, tokens.length);
            }

            String result = commandInterpreter.interpretCommand(command, newParams);
            System.out.println(result);
        }
    }

}