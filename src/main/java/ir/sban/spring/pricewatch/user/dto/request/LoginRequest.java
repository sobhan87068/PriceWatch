package ir.sban.spring.pricewatch.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginRequest {
    @NotEmpty(message = "{username.is.blank}")
    @Email(message = "{email.not.valid}")
    private String username;
    @NotEmpty(message = "{password.not.blank}")
    private String password;
}
