package com.subciber.configuracion.exception;

/**
 * @description Custom exception
 * @author David Villanueva
 * @version 0.1, 07/02/2019
 * @update
 */
public class BaseException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private Exception exception;
	private Integer codigo;
	private String mensaje;
	
	/**
	 * @param exception The exception object
	 */
	public BaseException(Exception exception) {
		super();
		this.exception = exception;
	}

    /**
	 * @param errorCode The error code
	 * @param errorMessage The error message
	 */
	public BaseException(Integer codigo, String mensaje) {
		super(mensaje);
		this.exception = this;
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	/**
	 * @param exception The exception object
	 * @param errorCode The error code
	 * @param errorMessage The error message
	 */
	public BaseException(Exception exception, Integer codigo, String mensaje) {
		super(mensaje);
		this.exception 	= exception;
		this.codigo 	= codigo;
		this.mensaje 	= mensaje;
	}


	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
