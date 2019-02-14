/**
 * 
 */
package com.subciber.configuracion.base.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class AuditRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String transaccionId;
	private String aplicacion;
	private Integer usuarioId;
	private String usuario;
	private String terminal;
	
	
	public String getTransaccionId() {
		return transaccionId;
	}
	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
	}
	public String getAplicacion() {
		return aplicacion;
	}
	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	@Override
	public String toString() {
		return "AuditRequestDto [transaccionId=" + transaccionId + ", aplicacion=" + aplicacion + ", usuarioId="
				+ usuarioId + ", usuario=" + usuario + ", terminal=" + terminal + "]";
	}
	
}
