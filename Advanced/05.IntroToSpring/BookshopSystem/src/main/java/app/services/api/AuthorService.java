package app.services.api;

import app.models.Author;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
public interface AuthorService extends ServiceInterface<Author> {
    List<Author> findAuthorsWithBooksBeforeYear(int i) throws ParseException;

    List<Object[]> findAuthorsOrderedByBooksCount();

    List<Object[]> findAuthorsOrderedByCopiesCount();

    List<Author> findAuthorsWithFirstNameEndingWith(String suffix);
}
