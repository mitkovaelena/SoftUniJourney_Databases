package app.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperWrapper {
    private static ModelMapper modelMapper;

    private MapperWrapper() { }

    @Bean
    public static ModelMapper getModelMapper() {
        return modelMapper == null ? (modelMapper = new ModelMapper()) : modelMapper;
    }
}
