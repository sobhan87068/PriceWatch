package ir.sban.spring.pricewatch.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProductResponse {
    private final long id;
    private final String name;
    private final String url;
    private final String currency;
}
