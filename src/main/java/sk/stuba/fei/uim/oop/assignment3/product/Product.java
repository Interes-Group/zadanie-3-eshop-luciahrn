package sk.stuba.fei.uim.oop.assignment3.product;



import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cart.Cart;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer amount;
    private String description;
    private String unit;
    private String name;
    private Integer price;


}
