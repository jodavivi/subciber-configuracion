/**
 * 
 */
package com.subciber.configuracion.base.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class AuditResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String transaccionId;
	private Integer codigoRespuesta;
	private String mensajeRespuesta;
	
	public String getTransaccionId() {
		return transaccionId;
	}
	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
	}
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}
	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}
	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}
	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
	
}
