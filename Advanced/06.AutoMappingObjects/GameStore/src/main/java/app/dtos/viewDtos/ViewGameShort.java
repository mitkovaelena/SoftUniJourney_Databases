package app.dtos.viewDtos;

import java.math.BigDecimal;

public class ViewGameShort {
    private String title;

    private BigDecimal price;

    public ViewGameShort() {
    }

    public ViewGameShort(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
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

    @Override
    public String toString() {
        return title + ' ' + price;
    }
}
