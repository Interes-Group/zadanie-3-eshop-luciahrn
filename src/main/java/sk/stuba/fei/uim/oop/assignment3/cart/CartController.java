package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @GetMapping()
    public List<CartResponse> getAll() {
        return this.service.getAll().stream().map(CartResponse::new).collect(Collectors.toList());
    }

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
}