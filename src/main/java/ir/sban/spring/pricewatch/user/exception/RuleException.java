package ir.sban.spring.pricewatch.user.exception;

import lombok.Getter;

@Getter
public class RuleException extends RuntimeException {
    private String code;
    public RuleException(String message, String code) {
        super(message);
        this.code = code;
    }
}
