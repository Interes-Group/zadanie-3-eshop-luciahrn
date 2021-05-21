package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    List<Cart> findAll();
}