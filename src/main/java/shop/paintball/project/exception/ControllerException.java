package shop.paintball.project.exception;

public class ControllerException extends RuntimeException {

    public ControllerException() {
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Throwable e) {
        super(e);
    }

    public ControllerException(String message, Throwable e) {
        super(message, e);
    }

}
