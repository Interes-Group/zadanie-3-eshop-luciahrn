package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Entity
@Getter
@Setter
@Embeddable
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    private long amount;


}
