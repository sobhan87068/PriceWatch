package ir.sban.spring.pricewatch.product.controller;

import ir.sban.spring.pricewatch.product.dto.request.ProductRequest;
import ir.sban.spring.pricewatch.product.dto.response.ProductCreationResponse;
import ir.sban.spring.pricewatch.product.dto.response.ProductResponse;
import ir.sban.spring.pricewatch.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductCreationResponse> create(@RequestBody @Valid ProductRequest product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable) {
        return ResponseEntity.ok(productService.getProducts(pageable));
    }
}
