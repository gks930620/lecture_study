package sample.cafekiosk.spring.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

class OrderTest {

    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    public  void   calculateTotalPrice () throws Exception {
        //given
        List<Product> products = List.of(createProduct("001", 1000),
            createProduct("002", 2000)
        );
        //when
        Order order= Order.create(products,LocalDateTime.now());
        assertThat(order.getTotalPrice()).isEqualTo(3000);
    }


    @DisplayName("주문 생성 시 주문상태는 INIT이다")
    @Test
    public  void   init () throws Exception {
        //given
        List<Product> products = List.of(createProduct("001", 1000),
            createProduct("002", 2000)
        );
        //when
        Order order= Order.create(products, LocalDateTime.now());
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.INIT);
    }



    @DisplayName("주문 생성 시 주문등록 시간을 기록한다.")
    @Test
    public  void   registeredDateTime () throws Exception {
        //given
        LocalDateTime registredDateTime=LocalDateTime.now();
        List<Product> products = List.of(createProduct("001", 1000),
            createProduct("002", 2000)
        );
        //when
        Order order= Order.create(products, registredDateTime);
        assertThat(order.getRegisteredDateTime()).isEqualTo(registredDateTime);
    }



    private Product createProduct( String productNumber,
        int price){
        return Product.builder()
            .type(ProductType.HANDMADE)
            .productNumber(productNumber)
            .price(price)
            .sellingStatus(ProductSellingStatus.SELLING)
            .name("메뉴 이름")
            .build();
    }



}