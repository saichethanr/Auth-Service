
package com.example.authentication_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;
    
    @NotBlank(message = "Password is required")
    private String password;
    
    private boolean isDriver;
    
    private CarDetailsDto carDetails;

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Please provide a valid email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Please provide a valid email") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") String password) {
        this.password = password;
    }

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public CarDetailsDto getCarDetails() {
        return carDetails;
    }

    public void setCarDetails(CarDetailsDto carDetails) {
        this.carDetails = carDetails;
    }
}
