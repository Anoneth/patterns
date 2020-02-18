package lab1.exception;

public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException(String message) {
        super(message);
    }
}