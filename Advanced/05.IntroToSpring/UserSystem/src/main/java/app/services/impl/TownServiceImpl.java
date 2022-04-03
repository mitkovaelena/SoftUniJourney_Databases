package app.services.impl;

import app.models.Town;
import app.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl extends BasicService<Town> implements TownService {
    @Autowired
    public TownServiceImpl(CrudRepository<Town, Long> repository) {
        super(repository);
    }
}
