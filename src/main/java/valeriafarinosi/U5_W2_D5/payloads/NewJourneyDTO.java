package valeriafarinosi.U5_W2_D5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewJourneyDTO(

        @NotBlank(message = "You must insert a destination for your journey, no blank space.")
        String destination,

        @NotNull(message = "You must insert a date for your journey, no blank space.")
        @FutureOrPresent(message = "The date of the journey can't be in the past.")
        LocalDate journeyDate

) {
}
