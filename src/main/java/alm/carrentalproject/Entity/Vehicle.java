package alm.carrentalproject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue
    private Long id;

    private enum Category {
        ECONOMY,
        LUXURY,
        TRUCK,
        VAN
    }

    @Enumerated(EnumType.STRING)
    private Vehicle.Category category = Vehicle.Category.ECONOMY;

    private enum Status {
        RENTED,
        AVAILABLE
    }

    @Enumerated(EnumType.STRING)
    private Vehicle.Status status = Vehicle.Status.AVAILABLE;

    @NotEmpty(message = "VIN can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^[A-HJ-NPR-Za-hj-npr-z\\d]{8}[\\dX][A-HJ-NPR-Za-hj-npr-z\\d]{2}\\d{6}$", message = "VIN format not correct!!")
    private String vin;

    @NotEmpty(message = "Plate can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^[A-Z]{1,3}-[A-Z]{1,2}-[0-9]{1,4}$", message = "Plate format not correct!!")
    private String plate;

    @NotEmpty(message = "Model name can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^(?![\\s.]+$)[a-zA-Z\\s.]*${1,20}$", message = "Model name can only contains letters and must be between 1-20 characters.")
    private String modelName;

    @NotNull(message = "Model year can not be empty!!")
    @Column(nullable = false)
    @Min(value = 2000, message = "Model year must be newer than 2000.")
    @Max(value = 2022, message = "Model year cannot be newer than 2022.")
    private Integer modelYear;

    @NotNull(message = "Mileage can not be empty!!")
    @Column(nullable = false)
    @Min(value = 0, message = "Mileage must be a positive number")
    @Max(value = 999999, message = "Mileage must be maximum 6 numbers.")
    private Integer mileage;

    @NotNull(message = "Capacity can not be empty!!")
    @Column(nullable = false)
    @Min(value = 2, message = "Capacity must be minimum 2.")
    @Max(value = 7, message = "Capacity must be maximum 7.")
    private Integer capacity;

    @NotNull(message = "Cost per Day can not be empty!!")
    @Column(nullable = false)
    @DecimalMax("300.00") @DecimalMin("10.00")
    private double costPerDay;

    @NotEmpty(message = "Image cannot be empty, you must provide an URL for image!!")
    private String imageURL;
}
