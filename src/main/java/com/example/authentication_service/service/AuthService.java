
package com.example.authentication_service.service;

import com.example.authentication_service.dto.AuthResponse;
import com.example.authentication_service.dto.LoginRequest;
import com.example.authentication_service.dto.RegisterRequest;
import com.example.authentication_service.dto.UserDto;
import com.example.authentication_service.entity.CarDetails;
import com.example.authentication_service.entity.User;
import com.example.authentication_service.exception.AuthException;
import com.example.authentication_service.repository.UserRepository;
import com.example.authentication_service.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) {
        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("Email is already registered");
        }
        
        // Create new user
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setDriver(request.isDriver());
        
        // Set car details if user is a driver
        if (request.isDriver() && request.getCarDetails() != null) {
            CarDetails carDetails = new CarDetails();
            carDetails.setMake(request.getCarDetails().getMake());
            carDetails.setModel(request.getCarDetails().getModel());
            carDetails.setYear(request.getCarDetails().getYear());
            carDetails.setColor(request.getCarDetails().getColor());
            carDetails.setLicensePlate(request.getCarDetails().getLicensePlate());
            user.setCarDetails(carDetails);
        }
        
        User savedUser = userRepository.save(user);
        
        // Generate JWT token
        String token = jwtProvider.generateToken(savedUser);
        
        return new AuthResponse(UserDto.fromUser(savedUser), token);
    }
    
    public AuthResponse login(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Get user from repository
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found"));
            
            // Generate token
            String token = jwtProvider.generateToken(user);
            
            return new AuthResponse(UserDto.fromUser(user), token);
        } catch (Exception e) {
            throw new AuthException("Invalid email or password");
        }
    }
}
