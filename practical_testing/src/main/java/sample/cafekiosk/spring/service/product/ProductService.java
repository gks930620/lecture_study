package sample.cafekiosk.spring.service.product;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private  final ProductRepository productRepository;


}
