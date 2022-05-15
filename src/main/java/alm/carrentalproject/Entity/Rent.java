package alm.carrentalproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "rents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Please choose a pickup date")
//    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pickup_date;

//    @NotNull(message = "Please choose a pickup time")
//    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalTime pickup_time;

//    @NotNull(message = "Please pick a drop date")
//    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date drop_date;

//    @NotNull(message = "Please pick a drop time")
//    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalTime drop_time;

    private enum RENT_STATUS {
        PENDING,
        COMPLETED,
        RENTED,
        RETURNED_ONTIME,
        RETURNED_LATE
    }

    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
    private RENT_STATUS status = RENT_STATUS.RENTED;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="insurance_id")
    private Insurance insurance;

}
