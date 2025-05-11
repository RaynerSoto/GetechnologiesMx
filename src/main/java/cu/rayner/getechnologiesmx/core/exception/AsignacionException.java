package cu.rayner.getechnologiesmx.core.exception;

/**
 * Esta es una excepción para la conversión entre objetos
 */
public class AsignacionException extends Exception{
    public AsignacionException() {
    }

    public AsignacionException(String message) {
        super(message);
    }

    public AsignacionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsignacionException(Throwable cause) {
        super(cause);
    }

    public AsignacionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
