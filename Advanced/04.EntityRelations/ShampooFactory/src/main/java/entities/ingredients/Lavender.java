package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient {
    private static final String NAME = "Lavender";

    private static final BigDecimal PRICE = BigDecimal.valueOf(2.00);

    public Lavender() {
        super(NAME, PRICE);
    }

}
