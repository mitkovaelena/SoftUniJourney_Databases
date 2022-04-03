package app.models;

import app.enums.AgeRestriction;
import app.enums.EditionType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReducedBook {
    private String title;
    private EditionType editionType;
    private BigDecimal price;
    private AgeRestriction ageRestriction;

    public ReducedBook(String title, EditionType editionType, BigDecimal price, AgeRestriction ageRestriction) {
        this.title = title;
        this.editionType = editionType;
        this.price = price;
        this.ageRestriction = ageRestriction;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    @Override
    public String toString() {
        return  "title= " + title +
                ", editionType= " + editionType +
                ", price= " + price +
                ", ageRestriction= " + ageRestriction;
    }
}
