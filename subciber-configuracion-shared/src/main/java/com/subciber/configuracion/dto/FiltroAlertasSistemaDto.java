/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class FiltroAlertasSistemaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer usuarioId;
	private String usuarioCodigo;
	private String usuario;
	private String estadoRecibido;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getUsuarioCodigo() {
		return usuarioCodigo;
	}
	public void setUsuarioCodigo(String usuarioCodigo) {
		this.usuarioCodigo = usuarioCodigo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEstadoRecibido() {
		return estadoRecibido;
	}
	public void setEstadoRecibido(String estadoRecibido) {
		this.estadoRecibido = estadoRecibido;
	}
	@Override
	public String toString() {
		return "FiltroAlertasSistemaDto [usuarioId=" + usuarioId + ", usuarioCodigo=" + usuarioCodigo + ", usuario="
				+ usuario + ", estadoRecibido=" + estadoRecibido + "]";
	}
}
