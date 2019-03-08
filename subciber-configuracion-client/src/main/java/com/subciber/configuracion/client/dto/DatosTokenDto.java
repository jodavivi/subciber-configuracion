package com.subciber.configuracion.client.dto;

import java.io.Serializable;

public class DatosTokenDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String session;
	private String usuario;
	private String usuarioId;
	private String correo;
	private String accesos;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getAccesos() {
		return accesos;
	}
	public void setAccesos(String accesos) {
		this.accesos = accesos;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

}