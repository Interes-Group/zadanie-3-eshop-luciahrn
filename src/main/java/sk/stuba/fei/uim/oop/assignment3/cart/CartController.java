package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.product.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;
    @Autowired
    private ProductController productController;



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse addProduct() {

        return new CartResponse(this.service.create());
    }

    private Optional<Cart> getCartFromService(Long id) {
        Optional<Cart> cart =this.service.getById(id);
        try{

                cart.get();

        }catch(NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
        return cart;
    }

    @GetMapping("/{id}")
    public Optional<Cart> getCart(@PathVariable("id") Long id) {
        Optional<Cart> cart = getCartFromService(id);
        return cart;

    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable("id") Long id) {
        getCartFromService(id);
        this.service.deleteCart(id);

    }

    @PostMapping("/{id}/add")
    public Optional<Cart> addProductToCart(@RequestBody ShoppingList newList,@PathVariable("id") Long id) {
        Optional<Cart> p=getCartFromService(id);
        if (p.get().isPayed()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List <ShoppingList> lists=this.service.getShoppingList(id);
        boolean inCart=false;
        Optional<Product> product=productController.getProductFromService(newList.getProductId());

         long newAmount= product.get().getAmount() - newList.getAmount();

         if(newAmount < 0) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"not enough products");
         }


        product.get().setAmount((int)newAmount);

        for (ShoppingList list: lists) {
            if (list.getProductId()==newList.getProductId()) {
                inCart=true;
                list.setAmount(list.getAmount()+ newList.getAmount());
            }

        }
        if (!inCart) {

            ShoppingList list = new ShoppingList();
            list.setProductId(newList.getProductId());
            list.setAmount(newList.getAmount());
            lists.add(list);
        }

        return p;

    }

    @GetMapping("/{id}/pay")
    public String payCart(@PathVariable("id") Long id) {
        Optional<Cart> cart=getCartFromService(id);
        if (cart.get().isPayed()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List <ShoppingList> list=this.service.getShoppingList(id);
        BigDecimal price;

        price = this.service.getPriceById(list);
        cart.get().setPayed(true);



        return price.toString();


    }
}
