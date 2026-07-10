package valeriafarinosi.U5_W2_D5.payloads;

import jakarta.validation.constraints.Positive;

public record NewBookingDTO(

        @Positive(message = "The worker id must be greater than 0.")
        int workerId,

        @Positive(message = "The journey id must be greater than 0.")
        int journeyId,

        String journeyPreferences


) {
}
