package alm.carrentalproject.Entity;

import com.sun.istack.NotNull;
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
    @Pattern(regexp = "^(?![\\s.]+$)[a-zA-Z\\s.]*${1,20}$", message = "First name can only contains letters and must be between 1-20 characters.")
    private String modelName;

    @NotEmpty(message = "Model year can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{4}", message = "Model year must be 4 numbers.")
    private Integer modelYear;

    @NotEmpty(message = "Mileage can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{6}", message = "Mileage must be maximum 6 numbers.")
    private Integer mileage;

    @NotEmpty(message = "Capacity can not be empty!!")
    @Column(nullable = false)
    @Pattern(regexp = "^[0-9]{1}", message = "Capacity must be 1 number.")
    private Integer capacity;

    @NotEmpty(message = "Cost per Day can not be empty!!")
    @Column(nullable = false)
    @Min(value = 10, message = "Cost per day should be minimum 10!!")
    @Max(value = 300, message = "Cost per day should be maximum 300!!")
    @Pattern(regexp = "^\\d+(,\\d{1,2})?$", message = "Cost per day must be a number.")
    private double costPerDay;
}
