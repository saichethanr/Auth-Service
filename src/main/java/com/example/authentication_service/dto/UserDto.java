package com.example.authentication_service.dto;

import com.example.authentication_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private boolean isDriver;
    private CarDetailsDto carDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public static UserDto fromUser(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setDriver(user.isDriver());
        
        if (user.getCarDetails() != null) {
            CarDetailsDto carDetailsDto = new CarDetailsDto();
            carDetailsDto.setMake(user.getCarDetails().getMake());
            carDetailsDto.setModel(user.getCarDetails().getModel());
            carDetailsDto.setYear(user.getCarDetails().getYear());
            carDetailsDto.setColor(user.getCarDetails().getColor());
            carDetailsDto.setLicensePlate(user.getCarDetails().getLicensePlate());
            dto.setCarDetails(carDetailsDto);
        }
        
        return dto;
    }
}
