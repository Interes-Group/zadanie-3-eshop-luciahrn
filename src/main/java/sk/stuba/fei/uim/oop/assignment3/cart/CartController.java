package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRequest;
import sk.stuba.fei.uim.oop.assignment3.product.ProductResponse;

import java.util.List;
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
}
