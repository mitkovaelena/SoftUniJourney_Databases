package app.services.impl;

import app.models.Picture;
import app.models.Town;
import app.services.api.PictureService;
import app.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl extends BasicService<Picture> implements PictureService {
    @Autowired
    public PictureServiceImpl(CrudRepository<Picture, Long> repository) {
        super(repository);
    }
}
