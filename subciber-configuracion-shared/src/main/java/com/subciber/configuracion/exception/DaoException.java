/**
 * 
 */
package com.subciber.configuracion.exception;

/**
 * @author josep
 *
 */
public class DaoException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param errorCode The error code
     * @param message The error message
     */
    public DaoException(Integer codigo, String mensaje) {
        super(codigo, mensaje);
    }

    /**
     * Constructor
     * @param errorCode The error code
     * @param errorMessage The error message
     * @param exception The exception object
     */
    public DaoException(Exception exception, Integer errorCode, String errorMessage) {
        super(exception, errorCode, errorMessage);
    }

}
