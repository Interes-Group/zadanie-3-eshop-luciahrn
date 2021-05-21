package sk.stuba.fei.uim.oop.assignment3.cart;


import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;

import java.util.List;

public interface ICartService {
    List<Cart> getAll();
    Cart getById(long id);

    Cart create();
}
