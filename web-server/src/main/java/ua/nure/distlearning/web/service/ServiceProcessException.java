package ua.nure.distlearning.web.service;

/**
 * @author Bohdan_Suprun
 */
public class ServiceProcessException extends RuntimeException {

    public ServiceProcessException() {
    }

    public ServiceProcessException(String message) {
        super(message);
    }

    public ServiceProcessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceProcessException(Throwable cause) {
        super(cause);
    }

    public ServiceProcessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
