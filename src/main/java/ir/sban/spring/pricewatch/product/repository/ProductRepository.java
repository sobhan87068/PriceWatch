package ir.sban.spring.pricewatch.product.repository;

import ir.sban.spring.pricewatch.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUrl(String url);
}
