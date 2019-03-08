/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @description Clase para filtros de autenticacion de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public class AutenticacionFiltroDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer estadoId;
	private String usuario;
	private String clave;
	private String path;
	private String session;
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
}
