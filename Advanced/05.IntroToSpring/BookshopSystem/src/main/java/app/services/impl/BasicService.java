package app.services.impl;

import app.enums.AgeRestriction;
import app.services.api.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public abstract class BasicService<E> implements ServiceInterface<E> {
    protected CrudRepository<E, Long> dao;

    @Autowired
    public BasicService(CrudRepository<E, Long> dao) {
        this.dao = dao;
    }

    @Override
    public E findByID(Long id) {
        return this.dao.findOne(id);
    }

    @Override
    public void remove(E object) {
        this.dao.delete(object);
    }

    @Override
    public Iterable<E> findAll() {
        return this.dao.findAll();
    }

    @Override
    public E save(E object) {
        return this.dao.save(object);
    }
}
