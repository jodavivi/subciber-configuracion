package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.List;

public class RequestRegistroAlertaSistemaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer estadoId;
	private Integer prioridadId;
	private Integer alertaTipoId;
	private String titulo;
	private String descripcion;
	private String estadoRecibido;
	private List<Integer> usuarioId;
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Integer getPrioridadId() {
		return prioridadId;
	}
	public void setPrioridadId(Integer prioridadId) {
		this.prioridadId = prioridadId;
	}
	public Integer getAlertaTipoId() {
		return alertaTipoId;
	}
	public void setAlertaTipoId(Integer alertaTipoId) {
		this.alertaTipoId = alertaTipoId;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstadoRecibido() {
		return estadoRecibido;
	}
	public void setEstadoRecibido(String estadoRecibido) {
		this.estadoRecibido = estadoRecibido;
	}
	public List<Integer> getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(List<Integer> usuarioId) {
		this.usuarioId = usuarioId;
	}
	@Override
	public String toString() {
		return "RequestRegistroAlertaSistemaDto [estadoId=" + estadoId + ", prioridadId=" + prioridadId
				+ ", alertaTipoId=" + alertaTipoId + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", estadoRecibido=" + estadoRecibido + ", usuarioId="
				+ usuarioId + "]";
	}
	
}
