package shop.paintball.project.exception;

public class DaoException extends RuntimeException {

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable e) {
        super(e);
    }

    public DaoException(String message, Throwable e) {
        super(message, e);
    }

}
