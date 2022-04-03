package app.services.impl;

import app.models.Author;
import app.repositories.AuthorRepository;
import app.services.api.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class AuthorServiceImpl extends BasicService<Author> implements AuthorService {

    @Autowired
    public AuthorServiceImpl(CrudRepository<Author, Long> repository) {
        super(repository);
    }

    @Override
    public List<Author> findAuthorsWithBooksBeforeYear(int year) throws ParseException {
        AuthorRepository ar =  (AuthorRepository) super.dao;
        return ar.findAuthorsWithBookReleasedBeforeYear(year);
    }

    @Override
    public List<Object[]> findAuthorsOrderedByBooksCount() {
        AuthorRepository ar =  (AuthorRepository) super.dao;
        return ar.findAllAuthorsOrderedByBooksCount();
    }

    @Override
    public List<Author> findAuthorsWithFirstNameEndingWith(String suffix) {
        AuthorRepository ar =  (AuthorRepository) super.dao;
        return ar.findByFirstNameEndingWith(suffix);
    }


    @Override
    public List<Object[]> findAuthorsOrderedByCopiesCount() {
        AuthorRepository ar =  (AuthorRepository) super.dao;
        return ar.findAllAuthorsOrderedByCopiesCount();
    }
}
