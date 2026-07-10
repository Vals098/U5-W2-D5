package valeriafarinosi.U5_W2_D5.payloads;

import jakarta.validation.constraints.NotNull;

public record JourneyUpdateStatusDTO(

        @NotNull(message = "Journey status must be added.")
        String journeyStatus

) {
}
