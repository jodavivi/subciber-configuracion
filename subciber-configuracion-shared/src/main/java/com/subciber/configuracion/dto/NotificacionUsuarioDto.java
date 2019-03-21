package com.subciber.configuracion.dto;

import java.io.Serializable;

public class NotificacionUsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer estadoId;
	private Integer notificacionId;
	private String usuarios;
	private Integer alertaTipoId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Integer getNotificacionId() {
		return notificacionId;
	}
	public void setNotificacionId(Integer notificacionId) {
		this.notificacionId = notificacionId;
	}
	public String getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}
	public Integer getAlertaTipoId() {
		return alertaTipoId;
	}
	public void setAlertaTipoId(Integer alertaTipoId) {
		this.alertaTipoId = alertaTipoId;
	}

}
