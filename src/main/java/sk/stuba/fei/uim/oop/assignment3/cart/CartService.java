package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    private CartRepository repository;


    @Autowired
    private ProductService productService;
    @Autowired
    public CartService(CartRepository repository) {


        this.repository = repository;



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
             if (p.isPresent()) {
                 price = price.add(p.get().getPrice().multiply(BigDecimal.valueOf(list.getAmount())));
             }
        }

        return  price;


    }

}
