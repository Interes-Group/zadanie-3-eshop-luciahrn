package sk.stuba.fei.uim.oop.assignment3.cart;


import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ICartService {

    Optional<Cart> getById(Long id);
    void deleteCart(Long id);
    Cart create();
    BigDecimal getPriceById(List<ShoppingList> shoppingLists);
    List<ShoppingList> getShoppingList(Long id);



}
