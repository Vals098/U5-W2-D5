package valeriafarinosi.U5_W2_D5.services;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import valeriafarinosi.U5_W2_D5.entities.Journey;
import valeriafarinosi.U5_W2_D5.exceptions.NotFoundException;
import valeriafarinosi.U5_W2_D5.payloads.NewJourneyDTO;
import valeriafarinosi.U5_W2_D5.repositories.JourneyRepository;

import java.util.List;

@Service
@Slf4j
public class JourneyService {

    //    JourneyRepository's DI
    private final JourneyRepository journeyRepository;

    public JourneyService(JourneyRepository journeyRepository) {
        this.journeyRepository = journeyRepository;
    }

    //  --------------------------  JOURNEYS CRUD -----------------------------
//    SAVE
    public Journey saveJourney(NewJourneyDTO payload) {
//        controls

//        create new journey
        Journey newJourney = new Journey(payload.destination(), payload.journeyDate());

//        save new journey and log message
        Journey saved = this.journeyRepository.save(newJourney);
        log.info("The journey " + newJourney + " has been saved!");
        return saved;

    }

    //    FINDALL
    public List<Journey> getAllJourneys() {
        return this.journeyRepository.findAll();
    }

    //    FINDBYID
    public Journey findById(int journeyId) {
        return this.journeyRepository.findById(journeyId).orElseThrow(() -> new NotFoundException(journeyId));
    }

    //   FINDBYID AND UPDATE
    public Journey findByIdAndUpdate(int journeyId, NewJourneyDTO payload) {

//        controls
        Journey found = this.journeyRepository.findById(journeyId).orElseThrow(() -> new NotFoundException(journeyId));

//        set payload data
        found.setDestination(payload.destination());
        found.setJourneyDate(payload.journeyDate());

//        save(update) journey
        Journey updated = this.journeyRepository.save(found);

        return updated;

    }

    //    DELETE
    public void findByIdAndDelete(int journeyId) {

//        controls
        Journey found = this.journeyRepository.findById(journeyId).orElseThrow(() -> new NotFoundException(journeyId));

//         delete
        this.journeyRepository.delete(found);

    }


}
