package app.interpreters;

import app.commands.api.Executable;

import java.lang.reflect.InvocationTargetException;

public interface Interpreter {
    String interpretCommand(String command, String ...params) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException;
}
