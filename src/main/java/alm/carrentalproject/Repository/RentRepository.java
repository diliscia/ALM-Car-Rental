package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.Rent;
import alm.carrentalproject.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Long> {

    @Query("Select v from Vehicle v Where v.status='AVAILABLE' ")
    public List<Vehicle> availableVehicles();
}
