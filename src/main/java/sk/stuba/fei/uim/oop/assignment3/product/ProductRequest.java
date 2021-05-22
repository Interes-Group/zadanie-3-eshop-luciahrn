package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProductRequest {
    private String description;
    private String name;
    private String unit;
    private Integer amount;
    private BigDecimal price;
}
