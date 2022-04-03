package entities.ingredients;

import java.io.Serializable;

public interface ChemicalIngredient extends Ingredient {
    String getChemicalFormula();

    void setChemicalFormula(String chemicalFormula);
}
