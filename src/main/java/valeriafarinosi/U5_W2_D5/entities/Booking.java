package valeriafarinosi.U5_W2_D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Getter
@ToString
public class Booking {

    @Id
    @GeneratedValue
    private int bookingId;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "journey_preferences")
    private String journeyPreferences;

    @ManyToOne
    @JoinColumn(name = "journey_id")
    private Journey journey;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    public Booking(Journey journey, Worker worker, LocalDate requestDate, String journeyPreferences) {
        this.journey = journey;
        this.worker = worker;
        this.requestDate = requestDate;
        this.journeyPreferences = journeyPreferences;
    }

    public void setJourneyPreferences(String journeyPreferences) {
        this.journeyPreferences = journeyPreferences;
    }
}
