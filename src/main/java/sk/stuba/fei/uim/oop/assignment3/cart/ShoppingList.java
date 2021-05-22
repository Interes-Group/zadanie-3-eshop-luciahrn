package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

import javax.persistence.*;
import java.util.List;

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
