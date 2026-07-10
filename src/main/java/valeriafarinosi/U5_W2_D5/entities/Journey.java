package valeriafarinosi.U5_W2_D5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import valeriafarinosi.U5_W2_D5.enums.JOURNEY_STATUS;

import java.time.LocalDate;

@Entity
@Table(name = "journeys")
@NoArgsConstructor
@Getter
@ToString
public class Journey {

    @Id
    @GeneratedValue
    private int journeyId;

    @Column(nullable = false)
    private String destination;

    @Column(name = "journey_date", nullable = false)
    private LocalDate journeyDate;

    @Column(name = "journey_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private JOURNEY_STATUS journeyStatus;

    public Journey(String destination, LocalDate journeyDate) {
        this.destination = destination;
        this.journeyDate = journeyDate;
        this.journeyStatus = JOURNEY_STATUS.PROGRAMMED;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setJourneyDate(LocalDate journeyDate) {
        this.journeyDate = journeyDate;
    }

    public void setJourneyStatus(JOURNEY_STATUS journeyStatus) {
        this.journeyStatus = journeyStatus;
    }
}
