
package com.example.authentication_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CarDetailsDto {
    @NotBlank(message = "Car make is required")
    private String make;
    
    @NotBlank(message = "Car model is required")
    private String model;
    
    @NotBlank(message = "Car year is required")
    private String year;
    
    @NotBlank(message = "Car color is required")
    private String color;
    
    @NotBlank(message = "License plate is required")
    private String licensePlate;

    public @NotBlank(message = "Car make is required") String getMake() {
        return make;
    }

    public void setMake(@NotBlank(message = "Car make is required") String make) {
        this.make = make;
    }

    public @NotBlank(message = "License plate is required") String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(@NotBlank(message = "License plate is required") String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public @NotBlank(message = "Car color is required") String getColor() {
        return color;
    }

    public void setColor(@NotBlank(message = "Car color is required") String color) {
        this.color = color;
    }

    public @NotBlank(message = "Car year is required") String getYear() {
        return year;
    }

    public void setYear(@NotBlank(message = "Car year is required") String year) {
        this.year = year;
    }

    public @NotBlank(message = "Car model is required") String getModel() {
        return model;
    }

    public void setModel(@NotBlank(message = "Car model is required") String model) {
        this.model = model;
    }
}
