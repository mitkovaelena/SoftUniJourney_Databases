package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component("JsonParser")
public class JsonParser implements Serializer {

    private Gson gson;

    @Autowired
    private FileParser fileParser;

    public JsonParser() {
        this.gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        this.fileParser = new FileParser();
    }

    @Override
    public <T> void serialize(T t, String fileName) {
        String json = gson.toJson(t);
        try {
            fileParser.writeFile(fileName, json);
        } catch (IOException e) {
            throw new SerializeException(json + " was not serialized to " + fileName, e);
        }
    }

    @Override
    public <T> T deserialize(Class<T> tClass, String fileName) {
        try {
            String file = this.fileParser.readFile(fileName);
            T object = this.gson.fromJson(file, tClass);
            return object;
        } catch (IOException e) {
            throw new SerializeException(fileName + " can not be read", e);
        }
    }
}
