package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;

import java.util.List;
import java.util.stream.Collectors;



// 보통 CUD보다 R이 훨씬많음
// 그래서 CUD용, R용 따로.   command/ read
// 그래서 service에 @Transactional readonly 하고  CUD쪽에만 readonly false 하는식.  (또는 모든 메소드마다 거는거. 이건 스타일)
//  더 나아가서는 DB를 2개로 나눔. master DB, slave DB(복사본)   ==>CUD작업이면 master로  readonly면 slave로.
@RequiredArgsConstructor
@Service
@Transactional (readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional (readOnly = false)
    public ProductResponse createProduct(ProductCreateRequest request) {
        //productNumber  001, 002, 003 , 004
        //DB에서 마지막 저장된 Product의 productNumber를 읽어와서 +1 해주는데 String
        String nextProductNumber=createnextProductNumber();
        Product product=request.toEntity(nextProductNumber);
        Product saveProduct=productRepository.save(product);

        return ProductResponse.of(saveProduct);
    }

    private String  createnextProductNumber() {
        String  latestProductNumber=productRepository.findLatestProductNumber();
        if(latestProductNumber==null){
            return "001";
        }
        int latestProductNumberInt=Integer.parseInt(latestProductNumber);
        int nextProductNumberInt=latestProductNumberInt+1;
        return String.format("%03d",nextProductNumberInt);  //3자리.  09  -> 009,  10 -> 010
    }


    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

}
