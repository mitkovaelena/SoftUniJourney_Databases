package entities.shampoos;

import entities.labels.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {
    private static final String BRAND = "Fresh Nuke";

    private static final BigDecimal PRICE = BigDecimal.valueOf(9.33);

    private static final Size SIZE = Size.BIG;

    public FreshNuke() {
    }

    public FreshNuke(BasicLabel label) {
        super(BRAND, PRICE, SIZE, label);
    }
}
