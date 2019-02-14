/**
 * 
 */
package com.subciber.configuracion.exception;

/**
 * @author josep
 *
 */
public class GeneralException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param errorCode The error code
     * @param message The error message
     */
    public GeneralException(Integer codigo, String mensaje) {
        super(codigo, mensaje);
    }

    /**
     * Constructor
     * @param errorCode The error code
     * @param errorMessage The error message
     * @param exception The exception object
     */
    public GeneralException(Exception exception, Integer errorCode, String errorMessage) {
        super(exception, errorCode, errorMessage);
    }

}
