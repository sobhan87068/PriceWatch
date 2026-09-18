package ir.sban.spring.pricewatch.product.repository;

import ir.sban.spring.pricewatch.product.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
}
