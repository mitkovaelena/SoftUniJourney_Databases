package app.dtos.viewDtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ViewGameDetailed {
    private String title;

    private BigDecimal price;

    private String description;

    private LocalDate releaseDate;

    public ViewGameDetailed() {
    }

    public ViewGameDetailed(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Title: " + title + '\n' +
                "Price: " + price.toString() + '\n' +
                "Description:" + description + '\n' +
                "ReleaseDate: " + releaseDate;
    }
}
