package valeriafarinosi.U5_W2_D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewWorkerDTO(

        @NotBlank(message = "You must insert a username, no blank space.")
        @Size(min = 2, max = 40, message = "The username must contain between 2 and 40 characters.")
        String username,

        @NotBlank(message = "You must insert a name, no blank space.")
        @Size(min = 2, max = 40, message = "The name must contain between 2 and 40 characters.")
        String name,

        @NotBlank(message = "You must insert a surname, no blank space.")
        @Size(min = 2, max = 40, message = "The surname must contain between 2 and 40 characters.")
        String surname,

        @NotBlank(message = "You must insert an email, no blank space.")
        @Email(message = "Email appropriate format required.")
        String email


) {

}
