package app.services.impl;

import app.models.Album;
import app.repositories.AlbumRepository;
import app.services.api.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AlbumServiceImpl extends BasicService<Album> implements AlbumService {
    private static final String DATE_FORMAT = "d/M/yyyy";

    @Autowired
    public AlbumServiceImpl(CrudRepository<Album, Long> repository) {
        super(repository);
    }


}
