/**
 * 
 */
package com.subciber.configuracion.business.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class EnvioCorreoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String correoDestino;
	private String asunto;
	private String cuerpo;
	public String getCorreoDestino() {
		return correoDestino;
	}
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getCuerpo() {
		return cuerpo;
	}
	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}
	
}

