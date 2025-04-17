package com.onlinebank.authservice.controller;

import com.onlinebank.common.dto.AuthRequest;
import com.onlinebank.common.dto.AuthResponse;
import com.onlinebank.common.dto.RegisterRequest;

import com.onlinebank.authservice.model.User;
import com.onlinebank.authservice.service.UserServiceImpl;
import com.onlinebank.common.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.onlinebank.authservice.repository.UserRepository;
import com.onlinebank.common.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userService;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse("Email déjà utilisé"));
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getByEmail(email);

        UserDTO dto = UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .build();

        return ResponseEntity.ok(dto);
    }
}
