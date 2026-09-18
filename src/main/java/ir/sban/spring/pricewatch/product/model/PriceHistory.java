package ir.sban.spring.pricewatch.product.model;

import ir.sban.spring.pricewatch.base.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceHistory extends BaseEntity {
    @DecimalMin(value = "0.0")
    private BigDecimal price;
    @ManyToOne
    private Product product;
}
