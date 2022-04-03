package app.interpreters;


import app.commands.api.Executable;
import app.services.api.GameService;
import app.services.api.UserService;
import app.utils.Session;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreter implements Interpreter {
    private static final String COMMANDS_PACKAGE = "app.commands.impl.";
    private static final String COMMAND_STRING = "Command";

    private UserService userService;
    private GameService gameService;
    private Session session;

    public CommandInterpreter(UserService userService, GameService gameService, Session session) {
        this.userService = userService;
        this.gameService = gameService;
        this.session = session;
    }

    @Override
    public String interpretCommand(String commandName, String... params) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        if (commandName.equals("LoginUser") || commandName.equals("RegisterUserDto")) {
            if (this.session.getLoggedInUser() != null) {
                return "There is already a logged user.";
            }
        } else if (!commandName.equals("AllGames") && !commandName.equals("DetailGame")){
            if (this.session.getLoggedInUser() == null) {
                return "No logged user";
            }
        }
        Class<Executable> commandClass = (Class<Executable>) Class.forName(COMMANDS_PACKAGE + commandName + COMMAND_STRING);
        Constructor<Executable> constructor = commandClass.getDeclaredConstructor(UserService.class, GameService.class);
        Executable command = constructor.newInstance(this.userService, this.gameService);
        return command.execute(params);
    }
}
