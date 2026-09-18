package ir.sban.spring.pricewatch.product.service;

import ir.sban.spring.pricewatch.product.dto.request.ProductRequest;
import ir.sban.spring.pricewatch.product.dto.response.ProductCreationResponse;
import ir.sban.spring.pricewatch.product.dto.response.ProductResponse;
import ir.sban.spring.pricewatch.product.model.PriceHistory;
import ir.sban.spring.pricewatch.product.model.Product;
import ir.sban.spring.pricewatch.product.repository.PriceHistoryRepository;
import ir.sban.spring.pricewatch.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final PriceHistoryRepository priceHistoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, PriceHistoryRepository priceHistoryRepository) {
        this.productRepository = productRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    @Override
    public ProductCreationResponse create(ProductRequest request) {
        Optional<Product> existing = productRepository.findByUrl(request.getUrl());
        if (existing.isPresent()) {
            throw new IllegalStateException("product.exists");
        }
        Product product = mapToProduct(request);

        PriceHistory priceHistory = mapToPriceHistory(request, product);
        product = productRepository.save(product);
        priceHistoryRepository.save(priceHistory);

        return mapToCreationResponse(product);
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(ProductServiceImpl::mapToProductResponse);
    }

    private static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .url(product.getUrl())
                .name(product.getName())
                .currency(product.getCurrency())
                .build();
    }

    private static ProductCreationResponse mapToCreationResponse(Product product) {
        return ProductCreationResponse.builder()
                .id(product.getId())
                .build();
    }

    private static PriceHistory mapToPriceHistory(ProductRequest request, Product product) {
        return PriceHistory.builder()
                .price(request.getPrice())
                .product(product)
                .build();
    }

    private static Product mapToProduct(ProductRequest product) {
        return Product.builder()
                .name(product.getName())
                .url(product.getUrl())
                .currency(product.getCurrency())
                .build();
    }
}
