package sample.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;
import sample.cafekiosk.spring.domain.stock.Stock;
import sample.cafekiosk.spring.domain.stock.StockRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private  final StockRepository stockRepository;

    @Transactional(readOnly = false)
    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        List<Product> products = findProductsBy(productNumbers);
        deductStockQuantities(products);
        Order order = Order.create(products, registeredDateTime);  //Order의 List<OrderProduct>에는 001p , 001p, 002P에 대한 order가 만들어짐.
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }

    private void deductStockQuantities(List<Product> products) {
        //재고 차감 체크가 필요한 상품들 filter
        List<String> stockProductNumbers = extractStockProductNumbers(products);

        // 재고 엔티티 조회
        List<Stock> stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers);
        Map<String, Stock> stockMap = createStockMapBy(stocks);

        // 상품별 counting
        Map<String, Long> productCountMap = createCountingMapBy(stockProductNumbers);

        // 재고 차감 시도
        deductAllProducts(stockProductNumbers, stockMap, productCountMap);
    }
    private static List<String> extractStockProductNumbers(List<Product> products) {
        List<String> stockProductNumbers = products.stream()
            .filter(product -> ProductType.containsStockType(product.getType()))
            .map(Product::getProductNumber)
            .collect(Collectors.toList());
        return stockProductNumbers;
    }

    private static Map<String, Long> createCountingMapBy(List<String> stockProductNumbers) {
        Map<String, Long> productCountMap = stockProductNumbers.stream()
            .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        return productCountMap;
    }

    private static Map<String, Stock> createStockMapBy(List<Stock> stocks) {
        Map<String, Stock> stockMap = stocks.stream()
            .collect(Collectors.toMap(Stock::getProductNumber, s -> s));
        return stockMap;
    }



    private  List<Product> findProductsBy( List<String> productNumbers) {
        // DB에는  IN으로 처리하니까 001,002 상품 2개 나옴
        List<Product> products = productRepository.findAllByProductNumberIn(
            productNumbers);
        //001-001Product,  002-002Product  map 생김
        Map<String, Product> productMap = products.stream()
            .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        // 다시 001, 001, 002에 대해 위의 map을 조회하면     duplicateProducts에는   001product,001product,002product이렇게  담김
        List<Product> duplicateProducts = productNumbers.stream()
            .map(productMap::get)
            .collect(Collectors.toList());
        return duplicateProducts;
    }

    private static void deductAllProducts(List<String> stockProductNumbers, Map<String, Stock> stockMap,
        Map<String, Long> productCountMap) {
        for(String stocuProductNumber : new HashSet<>(stockProductNumbers) ){
            Stock stock= stockMap.get(stocuProductNumber);
            int quantity = productCountMap.get(stocuProductNumber).intValue();
            if(stock.isQuantityLessThan(quantity)){
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
            }
            stock.deductQuantity(quantity);
        }
    }

}
