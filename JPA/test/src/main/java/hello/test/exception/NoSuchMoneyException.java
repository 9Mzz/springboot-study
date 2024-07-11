package hello.test.exception;

public class NoSuchMoneyException extends Exception {
    public NoSuchMoneyException() {
    }

    public NoSuchMoneyException(String message) {
        super(message);
    }
}
