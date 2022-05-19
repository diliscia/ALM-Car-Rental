package alm.carrentalproject.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
@Data
@NoArgsConstructor
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String date;

    private int number;
}
