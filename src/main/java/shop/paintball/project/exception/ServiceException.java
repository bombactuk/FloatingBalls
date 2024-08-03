package shop.paintball.project.exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable e) {
        super(e);
    }

    public ServiceException(String message, Throwable e) {
        super(message, e);
    }

}
