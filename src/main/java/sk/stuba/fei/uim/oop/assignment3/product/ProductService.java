package sk.stuba.fei.uim.oop.assignment3.product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService implements IProductService {


    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;

        Product a1 = new Product();

        a1.setName("Product1");
        a1.setAmount(10);
        this.repository.save(a1);
        Product a2 = new Product();

        a2.setName("Product2");
        this.repository.save(a2);
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

    @Override
    public Optional<Product> getById(Long id) {



        return this.repository.findById(id);
    }

    @Override
    public Product updateProduct(Long id,Product newProd) {

        Optional<Product> p=this.repository.findById(id);
        Product old=new Product();
        if (p.isPresent()) {
            old = p.get();
        }
        if (Objects.nonNull(newProd.getName())) {
            old.setName(newProd.getName());
        }
        if (Objects.nonNull(newProd.getDescription())) {
            old.setDescription(newProd.getDescription());
        }
        return this.repository.save(old);


    }

    @Override
    public void deleteProduct(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Integer getAmount(Long id) {
        Optional<Product> p=this.repository.findById(id);
        Product product =new Product();
        if (p.isPresent()) {
            product = p.get();
        }


        return product.getAmount();

    }

    @Override
    public Integer incrementAmount(Long id,Product newProd) {
        Optional<Product> p=this.repository.findById(id);
        Product old=new Product();
        if (p.isPresent()) {
            old = p.get();
        }
        Integer oldAmount=old.getAmount();
        old.setAmount(oldAmount+newProd.getAmount());
        this.repository.save(old);


        return old.getAmount();

    }



}
