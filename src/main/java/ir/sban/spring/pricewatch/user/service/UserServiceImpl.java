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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
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

        return mapToResponse(saved.getUsername());
    }

    @Override
    public UserResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        return mapToResponse(authentication.getName());
    }

    @Override
    public void updatePassword(String username, UpdatePasswordRequest request) {
        User user = userRepository.findByEmail(username).orElseThrow();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuleException("old.password.not.match", "old.password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    private UserResponse mapToResponse(String username) {
        return UserResponse.builder()
                .token(jwtUtil.generateToken(username))
                .build();
    }

    private @NonNull User mapToUser(SignupRequest request) {
        User user = new User();
        user.setEmail(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return user;
    }
}
