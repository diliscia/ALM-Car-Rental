package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.Rent;
import alm.carrentalproject.Entity.User;
import alm.carrentalproject.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

//    @Query("Select v from Vehicle v Where v.status='AVAILABLE' ")
//    public List<Vehicle> availableVehicles(String pickup_date,String drop_date);

    @Query(" Select v from Vehicle v where v not in " +
            "(Select v from Rent r  Join  r.vehicle v"+
           " Where  (r.pickup_date >= :pickup_date and r.pickup_date <= :drop_date )or " +
            "(r.drop_date between :pickup_date and :drop_date ) ) " )
        public List<Vehicle> availableVehicles(
            @Param("pickup_date")Date pickup_date,
            @Param("drop_date")Date drop_date);

}
