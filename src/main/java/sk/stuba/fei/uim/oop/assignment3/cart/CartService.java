package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    private CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
       /* List<ShoppingList> list=new ArrayList<>();
        ShoppingList newitem=new ShoppingList();
        newitem.setProductId(1);
        newitem.setAmount(500);
        list.add(newitem);

        Cart c=new Cart();
        c.setShoppingList(list);*/

        this.repository = repository;

        //this.repository.save(c);
        this.repository.save(new Cart(true));
        this.repository.save(new Cart(false));
        this.repository.save(new Cart(true));
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Cart getById(long id) {
        return this.repository.findById(id).orElseThrow();
    }



    @Override
    public Cart create() {
        Cart newCart = new Cart();
        newCart.setPayed(newCart.isPayed());
        newCart.setShoppingList(newCart.getShoppingList());


        return this.repository.save(newCart);
    }


}
