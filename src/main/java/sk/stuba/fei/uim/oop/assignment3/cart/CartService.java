package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    private CartRepository repository;

    @Autowired
    private ProductService productService;
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
        //this.repository.save(new Cart(false));
        //this.repository.save(new Cart(false));
        //this.repository.save(new Cart(true));
    }

    @Override
    public List<Cart> getAll() {
        return this.repository.findAll();
    }





    @Override
    public Cart create() {
        Cart newCart = new Cart();
        newCart.setPayed(newCart.isPayed());
        newCart.setShoppingList(newCart.getShoppingList());


        return this.repository.save(newCart);
    }

    @Override
    public Optional<Cart> getById(Long id) {


        //System.err.println("*********************"  +  id);
        //System.err.println("*********************"  +  this.repository.findById(id).get().isPayed());
        return this.repository.findById(id);
    }
    @Override
    public void deleteCart(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<ShoppingList> getShoppingList(Long id) {
        Optional<Cart> p=this.repository.findById(id);
        Cart cart = new Cart();
        if (p.isPresent()) {
            cart = p.get();
        }


        return cart.getShoppingList();

    }
    @Override
    public BigDecimal getPriceById(List<ShoppingList> shoppingLists) {
        BigDecimal price = BigDecimal.valueOf(0);
        for (ShoppingList list:  shoppingLists){
             Long id= list.getProductId();
             Optional<Product> p=productService.getById(id);
             price=price.add(p.get().getPrice().multiply(BigDecimal.valueOf(list.getAmount())));
        }

        return  price;



    }

    @Override
    public void updateCart(Optional<Cart> cart) {
        Cart cart1= cart.get();
        cart1.setPayed(true);
        this.repository.save(cart1);
    }





}
