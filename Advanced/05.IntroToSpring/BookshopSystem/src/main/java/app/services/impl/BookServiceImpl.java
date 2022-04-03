package app.services.impl;

import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.models.Author;
import app.models.Book;
import app.models.ReducedBook;
import app.repositories.BookRepository;
import app.services.api.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends BasicService<Book> implements BookService {
    private static final String DATE_FORMAT = "d/M/yyyy";

    @Autowired
    public BookServiceImpl(CrudRepository<Book, Long> repository) {
        super(repository);
    }

    @Override
    public List<Book> findTitlesAfterYear(int year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        Date releaseDate = formatter.parse("01/01/" + year);

        BookRepository br = (BookRepository) super.dao;
        return br.findAllByReleaseDateGreaterThanEqual(releaseDate);
    }


    @Override
    public List<Book> findBooksFromAuthorOrderedByDateDescTitleAsc(
            String firstName, String lastName) throws ParseException {

        BookRepository br = (BookRepository) super.dao;
        return br.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }

    @Override
    public List<Book> findBooksWithRestriction(String ageRestriction) {
        BookRepository br = (BookRepository) super.dao;
        return br.findByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> findGoldenEditionBooksWithLessThan5000Copies() {
        BookRepository br = (BookRepository) super.dao;
        return br.findByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000);
    }

    @Override
    public List<Book> findBooksNotReleasedIn(int year) {
        BookRepository br = (BookRepository) super.dao;
        return br.findNotReleasedIn(year);
    }

    @Override
    public int countBooksWithTitleLongerThan(int length) {
        BookRepository br = (BookRepository) super.dao;
        return br.countByTitleLongerThan(length);
    }

    @Override
    public ReducedBook getEditionTypePriceAndAgerestrictionByTitle(String title) {
        BookRepository br = (BookRepository) super.dao;
        return br.findReducedBookPerTitle(title);
    }

    @Override
    public int increaseCopiesOfBooksReleasedAfterBy(String date, int number) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        BookRepository br = (BookRepository) super.dao;
        return number*br.increaseCopiesReleasedAfterBy(formatter.parse(date), number);
    }
}
