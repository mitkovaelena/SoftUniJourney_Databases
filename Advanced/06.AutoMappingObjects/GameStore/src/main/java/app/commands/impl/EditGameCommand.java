package app.commands.impl;

import app.services.api.GameService;
import app.services.api.UserService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class EditGameCommand extends BaseCommand {

    private final Map<Class, Class> mapper = new LinkedHashMap<Class, Class>() {{
        put(int.class, Integer.class);
        put(double.class, Double.class);
        put(float.class, Float.class);
        put(long.class, Long.class);
        put(boolean.class, Boolean.class);
    }};

    public EditGameCommand(UserService userService, GameService gameService) {
        super(userService, gameService);
    }

    @Override
    public String execute(String... params) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String[] newParams = new String[params.length - 1];

        if (params.length > 1) {
            newParams = Arrays.copyOfRange(params, 1, params.length);
        }
        return this.getGameService().editGame(Long.parseLong(params[0]),newParams);
    }
}
