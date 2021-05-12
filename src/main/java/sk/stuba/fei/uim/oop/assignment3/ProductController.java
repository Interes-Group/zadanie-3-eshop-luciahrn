package sk.stuba.fei.uim.oop.assignment3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllAnimals() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
//        var result = new ArrayList<AnimalResponse>();
//        for(Animal a : this.service.getAll()) {
//            result.add(new AnimalResponse(a));
//        }
//        return result;
    }

    @PostMapping()
    public ProductResponse addAnimal(@RequestBody ProductRequest request) {
        return new ProductResponse(this.service.create(request));
    }

    @GetMapping("/{name}")
    public List<ProductResponse> getAllAnimalsByName(@PathVariable("name") String name) {
        return this.service.getAllByName(name).stream().map(ProductResponse::new).collect(Collectors.toList());
    }
}
