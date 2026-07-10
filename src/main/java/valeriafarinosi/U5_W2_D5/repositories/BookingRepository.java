package valeriafarinosi.U5_W2_D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriafarinosi.U5_W2_D5.entities.Booking;
import valeriafarinosi.U5_W2_D5.entities.Worker;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    boolean existsByWorkerAndJourney_JourneyDate(Worker worker, LocalDate journeyDate);
}
