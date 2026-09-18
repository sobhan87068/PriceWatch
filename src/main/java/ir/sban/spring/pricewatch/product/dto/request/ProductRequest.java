package ir.sban.spring.pricewatch.product.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotNull(message = "{product.name.null}")
    @NotBlank(message = "{product.name.blank}")
    private final String name;
    @NotNull(message = "{product.url.null}")
    @NotBlank(message = "{product.url.blank}")
    private final String url;
    @NotNull(message = "{product.currency.null}")
    @NotBlank(message = "{product.currency.blank}")
    private final String currency;
    @DecimalMin(value = "0.0", message = "{price.min}")
    private final BigDecimal price;
}
