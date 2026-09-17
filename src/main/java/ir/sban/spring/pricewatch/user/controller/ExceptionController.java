package ir.sban.spring.pricewatch.user.controller;

import ir.sban.spring.pricewatch.user.exception.ExceptionResponse;
import ir.sban.spring.pricewatch.user.exception.RuleException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {

    private final MessageSourceAccessor messageSourceAccessor;

    public ExceptionController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<List<ExceptionResponse>> handleRuleException(RuleException ruleException) {
        return ResponseEntity.status(400).body(Collections.singletonList(mapRuleException(ruleException)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<ExceptionResponse> response = mapMethodArgumentNotValidException(methodArgumentNotValidException);

        return ResponseEntity.status(402).body(response);
    }

    private List<ExceptionResponse> mapMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return methodArgumentNotValidException.getFieldErrors().stream().map(error ->
                ExceptionResponse.builder()
                .message(error.getDefaultMessage())
                .code(error.getField())
                .build()
                ).collect(Collectors.toList());
    }

    private ExceptionResponse mapRuleException(RuleException ruleException) {
        return ExceptionResponse.builder()
                .message(messageSourceAccessor.getMessage(ruleException.getMessage()))
                .code(ruleException.getCode())
                .build();
    }
}
