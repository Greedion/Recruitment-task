package logic;

public class ServiceOperationException extends RuntimeException {

    ServiceOperationException() {
    }

    public ServiceOperationException(String message) {
        super(message);
    }
}
