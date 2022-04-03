package app.commands.api;

public interface Executable {
    String execute(String ...params) throws NoSuchMethodException, IllegalAccessException, NoSuchFieldException;
}
