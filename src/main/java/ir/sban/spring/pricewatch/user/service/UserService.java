package ir.sban.spring.pricewatch.user.service;

import ir.sban.spring.pricewatch.user.dto.request.LoginRequest;
import ir.sban.spring.pricewatch.user.dto.request.SignupRequest;
import ir.sban.spring.pricewatch.user.dto.request.UpdatePasswordRequest;
import ir.sban.spring.pricewatch.user.dto.response.UserResponse;
import jakarta.validation.Valid;

public interface UserService {
    UserResponse save(SignupRequest request);
    UserResponse login(LoginRequest request);

    void updatePassword(@Valid UpdatePasswordRequest request);
}
