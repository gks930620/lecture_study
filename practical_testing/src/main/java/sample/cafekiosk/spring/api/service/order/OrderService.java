package sample.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        //request에 001,001, 002가 옴

        // DB에는  IN으로 처리하니까 001,002 상품 2개 나옴
        List<Product> products = productRepository.findAllByProductNumberIn(
            productNumbers);

        List<Product> duplicateProducts = findProductsBy(
            products, productNumbers);

        Order order = Order.create(duplicateProducts, registeredDateTime);  //Order의 List<OrderProduct>에는 001p , 001p, 002P에 대한 order가 만들어짐.
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }



    private  List<Product> findProductsBy(List<Product> products, List<String> productNumbers) {
        //001-001Product,  002-002Product  map 생김
        Map<String, Product> productMap = products.stream()
            .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        // 다시 001, 001, 002에 대해 위의 map을 조회하면     duplicateProducts에는   001product,001product,002product이렇게  담김
        List<Product> duplicateProducts = productNumbers.stream()
            .map(productMap::get)
            .collect(Collectors.toList());
        return duplicateProducts;
    }
}
