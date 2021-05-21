package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {

    private long id;
    private List<ShoppingList> shoppingList;
    private boolean payed;





    public CartResponse(Cart c) {
        this.id =c.getId();
        this.shoppingList=c.getShoppingList();
        //this.shoppingList = c.getShoppingList().stream().map(Product::getId).collect(Collectors.toList());
        //this.shoppingList = c.getShoppingList().stream().map(Product::getAmount).collect(Collectors.toList());
        this.payed = c.isPayed();

    }
}