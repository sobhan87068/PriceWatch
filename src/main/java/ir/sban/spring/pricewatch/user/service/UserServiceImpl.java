package ir.sban.spring.pricewatch.user.service;

import ir.sban.spring.pricewatch.user.dto.request.LoginRequest;
import ir.sban.spring.pricewatch.user.dto.request.SignupRequest;
import ir.sban.spring.pricewatch.user.dto.request.UpdatePasswordRequest;
import ir.sban.spring.pricewatch.user.dto.response.UserResponse;
import ir.sban.spring.pricewatch.user.exception.RuleException;
import ir.sban.spring.pricewatch.user.model.User;
import ir.sban.spring.pricewatch.user.repository.UserRepository;
import ir.sban.spring.pricewatch.user.util.JwtUtil;
import org.jspecify.annotations.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserResponse save(SignupRequest request) {
        if (!Objects.equals(request.getPassword(), request.getPasswordConfirm()))
            throw new RuleException("password.not.match", "password");

        User user = mapToUser(request);

        User existing = userRepository.findByEmail(request.getUsername()).orElse(null);
        if (existing != null) {
            throw new RuleException("username.exists", "username");
        }

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }

    @Override
    public UserResponse login(LoginRequest request) {
        User existing = userRepository.findByEmail(request.getUsername()).orElseThrow(
                () -> new RuleException("username.not.exist", "username")
        );

        if (!passwordEncoder.matches(request.getPassword(), existing.getPassword())) {
            throw new RuleException("username.not.exist", "username");
        }

        return mapToResponse(existing);
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request) {
//        User user = userRepository.
    }

    private UserResponse mapToResponse(User saved) {
        return UserResponse.builder()
                .token(jwtUtil.generateToken(saved.getEmail(),  saved.getId()))
                .build();
    }

    private @NonNull User mapToUser(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }
}
