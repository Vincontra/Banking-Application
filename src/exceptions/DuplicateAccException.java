package exceptions;

public class DuplicateAccException extends RuntimeException{
    public DuplicateAccException(String message) {
        super(message);
    }
}
