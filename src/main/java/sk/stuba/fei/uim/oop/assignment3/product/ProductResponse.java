package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

@Getter
public class ProductResponse {

    private Long id;
    private String description;
    private String name;
    private Integer amount;
    private String unit;
    private Integer price;



    public ProductResponse(Product a) {
        this.id = a.getId();
        this.description = a.getDescription();
        this.name = a.getName();
        this.amount=a.getAmount();
        this.unit=a.getUnit();
        this.price=a.getPrice();


    }
}