package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class FiltroNotificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer notificacionId;
	private String emisionEstado;
	private LocalDateTime emisionFecha;
	
	public String getEmisionEstado() {
		return emisionEstado;
	}
	public void setEmisionEstado(String emisionEstado) {
		this.emisionEstado = emisionEstado;
	}
	public LocalDateTime getEmisionFecha() {
		return emisionFecha;
	}
	public void setEmisionFecha(LocalDateTime emisionFecha) {
		this.emisionFecha = emisionFecha;
	}
	public Integer getNotificacionId() {
		return notificacionId;
	}
	public void setNotificacionId(Integer notificacionId) {
		this.notificacionId = notificacionId;
	}
	
}
