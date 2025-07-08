package sample.cafekiosk.spring.api.service.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest.ProductCreateRequestBuilder;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    @AfterEach
    void dbClear(){
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 상품번호에서 1증가한  값이다.")
    @Test
    public  void  createProduct() throws Exception {
        //given
        Product product1 = createProduct("001",ProductType.HANDMADE,ProductSellingStatus.SELLING,"아메리카노",3000);
        productRepository.saveAll(List.of(product1));
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(ProductType.HANDMADE).sellingStatus( ProductSellingStatus.SELLING)
            .name( "카푸치노").price(5000).build();
        //when
        ProductResponse productResponse = productService.createProduct(request);
        assertThat(productResponse)
            .extracting("productNumber" , "type","sellingStatus","name","price")
            .contains("002",ProductType.HANDMADE, ProductSellingStatus.SELLING,"카푸치노" , 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
            .extracting("productNumber" , "type","sellingStatus","name","price")
            .containsExactlyInAnyOrder(
                tuple("001",ProductType.HANDMADE, ProductSellingStatus.SELLING,"아메리카노" , 3000),
                tuple("002",ProductType.HANDMADE, ProductSellingStatus.SELLING,"카푸치노" , 5000)
            );
    }


    @DisplayName("상품이 하나도 없는 경우 신규상품을 등록한다. 그 때 상품번호는 001이다.")
    @Test
    public  void  createProductWhenEmpty() throws Exception {
        //given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(ProductType.HANDMADE).sellingStatus( ProductSellingStatus.SELLING)
            .name( "카푸치노").price(5000).build();
        //when
        ProductResponse productResponse = productService.createProduct(request);

        assertThat(productResponse)
            .extracting("productNumber" , "type","sellingStatus","name","price")
            .contains("001",ProductType.HANDMADE, ProductSellingStatus.SELLING,"카푸치노" , 5000);

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(1)
            .extracting("productNumber" , "type","sellingStatus","name","price")
            .containsExactlyInAnyOrder(
                tuple("001",ProductType.HANDMADE, ProductSellingStatus.SELLING,"카푸치노" , 5000)
            );

    }



    private static Product createProduct(String productNumber, ProductType productType,
        ProductSellingStatus status,
        String name , int price) {
        Product product1 = Product.builder()
            .productNumber(productNumber)
            .type(productType)
            .sellingStatus(status)
            .name(name)
            .price(price)
            .build();
        return product1;
    }


}