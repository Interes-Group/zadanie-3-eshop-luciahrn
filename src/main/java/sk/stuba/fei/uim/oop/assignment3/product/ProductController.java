package sk.stuba.fei.uim.oop.assignment3.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.cart.CartResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request) {

        return new ProductResponse(this.service.create(request));
    }

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product = getProductFromService(id);
        return product;

    }

    private Optional<Product> getProductFromService(Long id) {
        Optional<Product> product =this.service.getById(id);
        try{
            product.get();
        }catch(NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProductById(@RequestBody Product newProd ,@PathVariable("id") Long id) {
        getProductFromService(id);
        return this.service.updateProduct(id, newProd);
    }
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id) {
        getProductFromService(id);
        this.service.deleteProduct(id);

    }

    @GetMapping("/{id}/amount")
    public Amount getProductAmount(@PathVariable("id") Long id) {
        Amount a =new Amount();
        Optional<Product> p=getProductFromService(id);
        Integer productAmount=this.service.getAmount(id);
        a.setAmount(productAmount);
        return a;

    }

    @PostMapping("/{id}/amount")
    public Amount incrementProductAmount(@RequestBody Product newProd,@PathVariable("id") Long id) {
        Amount a =new Amount();
        Optional<Product> p=getProductFromService(id);
        Integer productAmount=this.service.incrementAmount(id,newProd);
        a.setAmount(productAmount);
        return a;

    }


}
