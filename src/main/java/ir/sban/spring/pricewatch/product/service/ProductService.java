package ir.sban.spring.pricewatch.product.service;

import ir.sban.spring.pricewatch.product.dto.request.ProductRequest;
import ir.sban.spring.pricewatch.product.dto.response.ProductCreationResponse;
import ir.sban.spring.pricewatch.product.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductCreationResponse create(ProductRequest product);

    Page<ProductResponse> getProducts(Pageable pageable);
}
