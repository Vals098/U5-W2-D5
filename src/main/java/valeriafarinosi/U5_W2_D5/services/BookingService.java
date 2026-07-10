package valeriafarinosi.U5_W2_D5.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valeriafarinosi.U5_W2_D5.entities.Booking;
import valeriafarinosi.U5_W2_D5.entities.Journey;
import valeriafarinosi.U5_W2_D5.entities.Worker;
import valeriafarinosi.U5_W2_D5.exceptions.BadRequestException;
import valeriafarinosi.U5_W2_D5.payloads.NewBookingDTO;
import valeriafarinosi.U5_W2_D5.repositories.BookingRepository;

import java.util.List;

@Service
@Slf4j
public class BookingService {

    //    BookingRepository's DI
    private final BookingRepository bookingRepository;
    private final WorkerService workerService;
    private final JourneyService journeyService;

    public BookingService(BookingRepository bookingRepository, WorkerService workerService, JourneyService journeyService) {
        this.bookingRepository = bookingRepository;
        this.workerService = workerService;
        this.journeyService = journeyService;
    }

    //    SAVE
    public Booking saveBooking(NewBookingDTO payload) {

//        worker and journey from db
        Worker worker = workerService.findById(payload.workerId());
        Journey journey = journeyService.findById(payload.journeyId());

//        controls if the worker already has a booking for that day
        if (bookingRepository.existsByWorkerAndJourney_JourneyDate(worker, journey.getJourneyDate())) {
            throw new BadRequestException("The worker " + worker.getName() + " already has a booking for the day: " + journey.getJourneyDate());
        }

//        create the booking
        Booking newBooking = new Booking(
                worker, journey, payload.journeyPreferences()
        );

//        save the booking in db
        Booking saved = this.bookingRepository.save(newBooking);

        log.info("The booking under the name: " + worker.getName() + " " + worker.getSurname() + " for the day: " + journey.getJourneyDate() + " has beeen saved!");

        return saved;

    }

    //    FINDALL
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }


}
