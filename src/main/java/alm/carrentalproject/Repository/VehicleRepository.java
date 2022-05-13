package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.User;
import alm.carrentalproject.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
