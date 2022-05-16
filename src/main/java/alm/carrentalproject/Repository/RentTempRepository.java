package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.RentTemp;
import alm.carrentalproject.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentTempRepository extends JpaRepository<RentTemp,Long> {

}
