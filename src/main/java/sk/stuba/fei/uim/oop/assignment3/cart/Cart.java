package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.product.Product;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter

@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public boolean isPayed() {
        return payed;
    }

    public boolean getPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    private boolean payed;


    @ElementCollection
    private List<ShoppingList> shoppingList = new ArrayList<>();





}
