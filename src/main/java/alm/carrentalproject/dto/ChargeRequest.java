package alm.carrentalproject.dto;

import lombok.Data;
import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Currency;

@Data
public class ChargeRequest {
    private String description;
    private double amoutn;
    private Currency currency;

}
