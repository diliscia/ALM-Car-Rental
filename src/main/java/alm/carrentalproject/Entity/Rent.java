package alm.carrentalproject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "rents")
@Data
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Please choose a pickup date")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate pickup_date;


    @NotEmpty(message = "Please choose a pickup time")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalTime pickup_time;

    @NotEmpty(message = "Please pick a drop date")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDate drop_date;

    @NotEmpty(message = "Please pick a drop time")
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalTime drop_time;

    private enum rent_status {
        RENTED,
        RETURNED_ONTIME,
        RETURNED_LATE
    }

    @Enumerated(EnumType.STRING)
    private Rent.rent_status status = rent_status.RENTED;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

}
