package entities.ingredients;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "MN")
public class Mint extends BasicIngredient {
    private static final String NAME = "Mint";

    private static final BigDecimal PRICE = BigDecimal.valueOf(3.54);

    public Mint() {
        super(NAME, PRICE);
    }

}
