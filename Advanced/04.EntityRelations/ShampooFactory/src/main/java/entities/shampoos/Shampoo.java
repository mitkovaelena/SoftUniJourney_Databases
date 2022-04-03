package entities.shampoos;

import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;
import enums.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {
    long getId();

    void setId(long id);

    String getBrand();

    void setBrand(String brand);

    BasicLabel getLabel();

    void setLabel(BasicLabel label);

    //ProductionBatch getBatch();

    //void setBatch(ProductionBatch batch);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    Set<BasicIngredient> getIngredients();

    void setIngredients(Set<BasicIngredient> ingredients);
}
