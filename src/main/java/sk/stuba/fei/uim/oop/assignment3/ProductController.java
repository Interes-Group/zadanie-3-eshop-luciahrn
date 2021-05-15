package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.ArrayList;
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
    public ProductResponse addProduct(@RequestBody ProductRequest request) {
        return new ProductResponse(this.service.create(request));
    }

  /* @GetMapping("/{name}")
    public List<ProductResponse> getAllPoductsByName(@PathVariable("name") String name) {
        return this.service.getAllByName(name).stream().map(ProductResponse::new).collect(Collectors.toList());
    }*/

    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") Long id) {
        Optional<Product> product =this.service.getById(id);
        try{
            product.get();
        }catch(NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
        return product;

    }

    @PutMapping("/{id}")
    public Optional<Product> updateProductById(@RequestBody Product newProd ,@PathVariable("id") Long id) {
        return this.service.updateProduct(id, newProd);
    }
}
