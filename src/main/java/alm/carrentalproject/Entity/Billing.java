package alm.carrentalproject.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "billing")
@Data
@NoArgsConstructor
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="Bill date cannot be null and not before the current date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bill_date;

    private enum Status {
        NOT_PAID,
        PAID
    }
    @Enumerated(EnumType.STRING)
    private Status isPaid = Status.NOT_PAID;

    @NotNull(message="Due date cannot be null and not before the current date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date due_date;

    @NotEmpty(message = "Amount can not be empty!!")
    @Column(nullable = false)
    @Min(value = 10, message = "Cost per day should be minimum 10!!")
    @Pattern(regexp = "^\\d+(,\\d{1,2})?$", message = "Cost per day must be a number.")
    private double amount;

    @Column(nullable = true)
    @Pattern(regexp = "^\\d+(,\\d{1,2})?$", message = "Late Fee must be a number.")
    private double late_fee;

    @ManyToOne(fetch=FetchType.LAZY,optional = false )
    @JoinColumn(name="rent_id")
    private Rent rent;

    public Billing(Date bill_date, Status isPaid, Date due_date, double amount, double late_fee, Rent rent) {
        this.bill_date = bill_date;
        this.isPaid = isPaid;
        this.due_date = due_date;
        this.amount = amount;
        this.late_fee = late_fee;
        this.rent = rent;
    }
}
