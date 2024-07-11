package hello.test.exception;

public class NoSuchQuantityException extends RuntimeException {
    public NoSuchQuantityException() {
    }

    public NoSuchQuantityException(String message) {
        super(message);
    }
}
