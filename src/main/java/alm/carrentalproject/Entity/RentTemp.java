package alm.carrentalproject.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name="renttemp")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentTemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date pickup_date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickup_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date drop_date;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime drop_time;

}
