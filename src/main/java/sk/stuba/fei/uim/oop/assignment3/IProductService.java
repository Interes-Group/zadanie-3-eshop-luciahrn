package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.expression.spel.ast.OpInc;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    List<Product> getAllByName(String name);
    Optional<Product> getById(Long id);
    Optional<Product> updateProduct(Long id,Product product);
}
