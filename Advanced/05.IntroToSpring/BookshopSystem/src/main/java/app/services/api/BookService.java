package app.services.api;

import app.enums.AgeRestriction;
import app.models.Author;
import app.models.Book;
import app.models.ReducedBook;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

@Service
public interface BookService extends ServiceInterface<Book>{
    List<Book> findTitlesAfterYear(int i) throws ParseException;

    List<Book> findBooksFromAuthorOrderedByDateDescTitleAsc(
            String firstName, String lastName) throws ParseException;

    List<Book> findBooksWithRestriction(String ageRestriction);

    List<Book> findGoldenEditionBooksWithLessThan5000Copies();

    List<Book> findBooksNotReleasedIn(int year);

    int countBooksWithTitleLongerThan(int length);

    ReducedBook getEditionTypePriceAndAgerestrictionByTitle(String title);

    int increaseCopiesOfBooksReleasedAfterBy(String date, int number) throws ParseException;

}
