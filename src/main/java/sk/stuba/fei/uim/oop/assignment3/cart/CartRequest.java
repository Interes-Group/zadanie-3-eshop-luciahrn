package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartRequest {
    private boolean payed;
    private List<ShoppingList> shoppingList;
}
