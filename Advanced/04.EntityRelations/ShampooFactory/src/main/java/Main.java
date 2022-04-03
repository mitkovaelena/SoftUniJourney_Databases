import entities.ingredients.AmmoniumChloride;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.labels.BasicLabel;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FiftyShades;
import entities.shampoos.FreshNuke;
import entities.shampoos.PinkPanther;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        /*EntityManagerFactory emf = Persistence.createEntityManagerFactory("shampoo_factory");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        BasicIngredient am = new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo", "Shampoo");
        BasicLabel label2 = new BasicLabel("Pink Panter Shampoo", "Shampoo");

        BasicShampoo shampoo = new FreshNuke(label);
        BasicShampoo shampoo2 = new PinkPanther(label2);
        BasicShampoo shampoo3 = new FiftyShades(label);
        shampoo.getIngredients().add(am);
        shampoo.getIngredients().add(mint);
        shampoo.getIngredients().add(nettle);

        em.persist(shampoo);
        em.persist(shampoo2);
        em.persist(shampoo3);

        em.getTransaction().commit();
        em.close();
        ;*/
    }
}
