/**
 * 
 */
package com.subciber.configuracion.client.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */

public class EstructuraTokensDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tokens;
	private String nuevoTokens;
	private String session;
	private Integer usuarioId;
	private String usuario;
	private String email;
	private String fechaCreacion;
	private String fechaExpiracion;
	private String codigodeaccesos;
	private String rol;
	
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public String getCodigodeaccesos() {
		return codigodeaccesos;
	}
	public void setCodigodeaccesos(String codigodeaccesos) {
		this.codigodeaccesos = codigodeaccesos;
	}
	public String getNuevoTokens() {
		return nuevoTokens;
	}
	public void setNuevoTokens(String nuevoTokens) {
		this.nuevoTokens = nuevoTokens;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
}
