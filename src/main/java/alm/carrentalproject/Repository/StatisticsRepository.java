package alm.carrentalproject.Repository;

import alm.carrentalproject.Entity.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    @Query(value = "SELECT id, date_format(r.pickup_date,'%Y-%m-%d') as 'date'  ,count(r.pickup_date) as 'number'  " +
            " FROM rents r group by r.pickup_date order by r.pickup_date",nativeQuery = true)
     List<Statistics> getAll();
}
