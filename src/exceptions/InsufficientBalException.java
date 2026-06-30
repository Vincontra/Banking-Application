package exceptions;

public class InsufficientBalException extends RuntimeException {
    public InsufficientBalException(String message) {
        super(message);
    }
}
