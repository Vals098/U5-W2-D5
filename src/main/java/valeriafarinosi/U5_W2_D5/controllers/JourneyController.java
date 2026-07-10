package valeriafarinosi.U5_W2_D5.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valeriafarinosi.U5_W2_D5.entities.Journey;
import valeriafarinosi.U5_W2_D5.exceptions.ValidationException;
import valeriafarinosi.U5_W2_D5.payloads.JourneyResponseDTO;
import valeriafarinosi.U5_W2_D5.payloads.JourneyUpdateStatusDTO;
import valeriafarinosi.U5_W2_D5.payloads.NewJourneyDTO;
import valeriafarinosi.U5_W2_D5.services.JourneyService;

import java.util.List;

@RestController
@RequestMapping("/journeys")
public class JourneyController {

    //    JourneyService's DI
    private final JourneyService journeyService;

    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    //    POST http://localhost:3003/journeys + payload -> newJourney
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public JourneyResponseDTO createJourney(@RequestBody @Validated NewJourneyDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }

        Journey saved = this.journeyService.saveJourney(payload);
        return new JourneyResponseDTO(saved.getJourneyId());

    }

    //    GET http://localhost:3003/journeys -> List<Journey>
    @GetMapping
    public List<Journey> getAllJourneys() {
        return this.journeyService.getAllJourneys();
    }

    //    GET http://localhost:3003/journeys/{journeyId} -> Journey
    @GetMapping("/{journeyId}")
    public Journey findById(@PathVariable int journeyId) {
        return this.journeyService.findById(journeyId);
    }

    //    PUT http://localhost:3003/journeys/{journeyId} + payload -> updated Journey with journeyId
    @PutMapping("/{journeyId}")
    public Journey findByIdAndUpdate(@PathVariable int journeyId, @RequestBody @Validated NewJourneyDTO payload, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            List<String> errorsList = validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
            throw new ValidationException(errorsList);
        }

        return this.journeyService.findByIdAndUpdate(journeyId, payload);

    }

    //    DELETE http://localhost:3003/journeys/{journeyId} -> delete Journey with journeyId
    @DeleteMapping("/{journeyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable int journeyId) {
        this.journeyService.findByIdAndDelete(journeyId);
    }

    //    PATCH http://localhost:3003/journeys/{journeyId}/status + payload -> update status of a Journey
    @PatchMapping("/{journeyId}/status")
    public JourneyResponseDTO updateJourneyStatus(@PathVariable int journeyId, @RequestBody @Validated JourneyUpdateStatusDTO payload) {

        Journey updated = journeyService.updateStatus(journeyId, payload);

        return new JourneyResponseDTO(updated.getJourneyId());
    }


}
