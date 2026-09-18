package ir.sban.spring.pricewatch.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@Builder
public class SignupRequest {
    @NotNull(message = "{username.is.null}")
    @NotBlank(message = "{username.is.blank}")
    @Email(message = "{email.not.valid}")
    private final String username;
    @NotNull(message = "{password.is.null}")
    @NotBlank(message = "{password.is.blank}")
    @Length(min = 10, message = "{password.min.length}")
    private final String password;
    @NotNull(message = "{password.is.null}")
    @NotBlank(message = "{password.is.blank}")
    @Length(min = 10, message = "{password.min.length}")
    private final String passwordConfirm;
}
