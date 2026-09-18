package ir.sban.spring.pricewatch.product.model;

import ir.sban.spring.pricewatch.base.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product extends BaseEntity {
    @NotNull
    @NotBlank
    private String name;
    @Column(unique = true)
    @NotNull
    @NotBlank
    private String url;
    @NotNull
    @NotBlank
    private String currency;
}
