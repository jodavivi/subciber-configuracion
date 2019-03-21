package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NotificacionDetalleDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer alertaTipoId;
	private String alertaTipo;
	private String accion;
	private List<String> usuarios;
	
	public NotificacionDetalleDto() {
		super();
		usuarios = new ArrayList<String>();
	}
	
	public Integer getAlertaTipoId() {
		return alertaTipoId;
	}
	public void setAlertaTipoId(Integer alertaTipoId) {
		this.alertaTipoId = alertaTipoId;
	}
	public String getAlertaTipo() {
		return alertaTipo;
	}
	public void setAlertaTipo(String alertaTipo) {
		this.alertaTipo = alertaTipo;
	}
	public List<String> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<String> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
