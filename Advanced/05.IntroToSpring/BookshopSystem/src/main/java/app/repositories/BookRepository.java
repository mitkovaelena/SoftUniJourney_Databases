package app.repositories;

import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.models.Author;
import app.models.Book;
import app.models.ReducedBook;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByReleaseDateGreaterThanEqual(Date date);


    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(
            String firstName, String lastName);

    List<Book> findByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    @Query("SELECT b FROM Book AS b WHERE year(b.releaseDate) != :year")
    List<Book> findNotReleasedIn(@Param("year") int year);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE LENGTH(b.title) > :length")
    int countByTitleLongerThan(@Param("length") int length);

    @Query("SELECT new app.models.ReducedBook(b.title, b.editionType, b.price, b.ageRestriction) FROM Book AS b WHERE b.title = :title")
    ReducedBook findReducedBookPerTitle(@Param("title") String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies+ :num WHERE b.releaseDate > :date")
    int increaseCopiesReleasedAfterBy(@Param("date") Date date, @Param("num") int num);
}
