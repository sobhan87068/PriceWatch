package ir.sban.spring.pricewatch.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdatePasswordRequest {
    private final String oldPassword;
    private final String newPassword;
}
