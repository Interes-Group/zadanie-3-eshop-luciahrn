package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    List<Product> getAllByName(String name);
    Optional<Product> getById(Long id);
    Product updateProduct(Long id,Product product);
    void deleteProduct(Long id);
    Integer getAmount(Long id);
    Integer incrementAmount(Long id,Product newProd);
    public Integer decrementAmount(Long id,Product newProd);
}
