package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    @Query ("Select b from Billing b JOIN b.rent r JOIN r.user u WHERE u.id = :userId")
    List<Billing> findBillingList(@Param("userId") Long userId);

}
