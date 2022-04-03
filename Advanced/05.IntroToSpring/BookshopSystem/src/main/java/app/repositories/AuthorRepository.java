package app.repositories;

import app.models.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query("SELECT a.firstName, a.lastName, COUNT(a) AS book_count FROM Author AS a " +
            "INNER JOIN a.books " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY book_count DESC ")
    List<Object[]> findAllAuthorsOrderedByBooksCount();

    @Query("SELECT DISTINCT a FROM Author AS a INNER JOIN a.books AS b WHERE year(b.releaseDate) > :year")
    List<Author> findAuthorsWithBookReleasedBeforeYear(@Param("year") int year);

    List<Author> findByFirstNameEndingWith(String suffix);

    @Query("SELECT a.firstName, a.lastName, SUM(b.copies) AS copies_count FROM Author AS a " +
            "INNER JOIN a.books AS b " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY copies_count DESC ")
    List<Object[]> findAllAuthorsOrderedByCopiesCount();
}
