package sk.stuba.fei.uim.oop.assignment3;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {


    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;

        Product a1 = new Product();

        a1.setName("Product1");
        this.repository.save(a1);
        //Product a2 = new Product();

        //a2.setName("Felix");
        //this.repository.save(a2);
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setDescription(request.getDescription());
        newProduct.setName(request.getName());
        newProduct.setAmount(request.getAmount());
        newProduct.setPrice(request.getPrice());
        newProduct.setUnit(request.getUnit());

        return this.repository.save(newProduct);
    }

    @Override
    public List<Product> getAllByName(String name) {
        return this.repository.findByName(name);
    }
}
