package sample.cafekiosk.spring.api.controller.product.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;


@Getter
public class ProductCreateRequest {
    //private String productNumber; 이건 따로 자동으로 만드는로직이있음
    private ProductType type;
    private ProductSellingStatus sellingStatus;
    private String name;
    private int price;

    @Builder
    public ProductCreateRequest(ProductType type, ProductSellingStatus sellingStatus, String name,
        int price) {
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder().productNumber(nextProductNumber)
            .type(this.type)
            .sellingStatus(this.sellingStatus)
            .name(this.name)
            .price(this.price)
            .build();
    }
}
