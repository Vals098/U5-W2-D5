package valeriafarinosi.U5_W2_D5.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {

    private List<String> errorsList;

    public ValidationException(List<String> errorsList) {
        super("Validation Errors");
        this.errorsList = errorsList;
    }

    public ValidationException(String message) {
        super(message);
    }
}
