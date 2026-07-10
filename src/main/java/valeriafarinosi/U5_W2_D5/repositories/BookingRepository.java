package valeriafarinosi.U5_W2_D5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import valeriafarinosi.U5_W2_D5.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
