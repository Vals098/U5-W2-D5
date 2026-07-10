package valeriafarinosi.U5_W2_D5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super("The id " + id + " did not match with anything");
    }
}
