package app;

import app.enums.AgeRestriction;
import app.enums.EditionType;
import app.models.Author;
import app.models.Book;
import app.models.Category;
import app.services.impl.AuthorServiceImpl;
import app.services.impl.BookServiceImpl;
import app.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final String DATE_FORMAT = "d/M/yyyy";

    private AuthorServiceImpl authorService;
    private BookServiceImpl bookService;
    private CategoryServiceImpl categoryService;
    private Random random = new Random();

    @Autowired
    public ConsoleRunner(AuthorServiceImpl authorService, BookServiceImpl bookService, CategoryServiceImpl categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... strings) throws Exception {
       //seedDatabase();
       //writeQueries();
       //ex1();
       //ex2();
       //ex4();
       //ex6();
       //ex9();
       //ex10();
       //ex11();
        ex12();
    }

    private void ex12() throws ParseException {
        System.out.println(this.bookService.increaseCopiesOfBooksReleasedAfterBy("06/06/2013", 44));
    }

    private void ex11() {
        System.out.println(this.bookService.getEditionTypePriceAndAgerestrictionByTitle("Thrones"));
    }

    private void ex10() {
        this.authorService.findAuthorsOrderedByBooksCount()
                .forEach(arr -> System.out.println(Arrays.deepToString(arr)));
    }

    private void ex9() {
        System.out.println(this.bookService.countBooksWithTitleLongerThan(12));
    }

    private void ex6() {
        this.authorService.findAuthorsWithFirstNameEndingWith("dy")
                .forEach(a-> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void ex4() {
        this.bookService.findBooksNotReleasedIn(1998).
                forEach(b-> System.out.println(b.getTitle()));
    }

    private void ex2() {
        this.bookService.findGoldenEditionBooksWithLessThan5000Copies().
                forEach(b-> System.out.println(b.getTitle()));
    }

    private void ex1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ageRestriction = reader.readLine();

        this.bookService.findBooksWithRestriction(ageRestriction)
                .forEach(b -> System.out.println(b.getTitle()));

    }

    private void writeQueries() throws ParseException {
        //bookService.findTitlesAfterYear(2000)
        //        .forEach(b -> System.out.println(b.getTitle() + " " + b.getReleaseDate()));

        //authorService.findAuthorsWithBooksBeforeYear(1990)
        //        .forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));

        //authorService.findAuthorsOrderedByBooksCount()
        //        .forEach(arr -> System.out.println(Arrays.deepToString(arr)));

        bookService.findBooksFromAuthorOrderedByDateDescTitleAsc("George", "Powell")
                .forEach(b -> System.out.println(
                        b.getTitle() + " " + b.getAuthor().getLastName() + " "
                                + b.getReleaseDate() +" "+ b.getCopies()));
    }

    private void seedDatabase() throws IOException, ParseException {
        List<Author> authors = new ArrayList<>();

        BufferedReader authorsReader = new BufferedReader(new FileReader("src/main/resources/authors.txt"));
        String line;
        while ((line = authorsReader.readLine()) != null) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);

            authors.add(author);

            authorService.save(author);
        }

        List<Category> categories = new ArrayList<>();

        BufferedReader categoriesReader = new BufferedReader(new FileReader("src/main/resources/categories.txt"));
        while ((line = categoriesReader.readLine()) != null) {
            Category category = new Category();
            category.setName(line);

            categories.add(category);

            categoryService.save(category);
        }

        BufferedReader booksReader = new BufferedReader(new FileReader("src/main/resources/books.txt"));
        while ((line = booksReader.readLine()) != null) {
            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.valueOf(data[4])];
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date releaseDate = formatter.parse(data[1]);
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setDescription(title + " description");
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);

            int categoryIndex = random.nextInt(categories.size());
            List<Category> bookCategories = new ArrayList<>();
            for (int i = categoryIndex; i >=0 ; i--) {
                bookCategories.add(categories.get(categoryIndex));
                categoryIndex=random.nextInt(categories.size());
            }
            book.setCategories(bookCategories);

            bookService.save(book);
        }

    }
}
