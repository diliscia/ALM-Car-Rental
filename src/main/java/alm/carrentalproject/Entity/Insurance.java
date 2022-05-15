package alm.carrentalproject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "insurances")
@Data
@NoArgsConstructor
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please enter an insurance name")
    private String name;

    @NotEmpty(message = "Please enter an insurance type")
    private String type;

    @NotEmpty(message = "Cost per Day can not be empty!!")
    @Column(nullable = false)
    @Min(value = 10, message = "Cost per day should be minimum 10!!")
    @Max(value = 50, message = "Cost per day should be maximum 50!!")
    @Pattern(regexp = "^\\d+(,\\d{1,2})?$", message = "Cost per day must be a number.")
    private double costPerDay;

}
