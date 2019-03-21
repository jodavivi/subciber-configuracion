package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.List;

public class CrearNotificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer estadoId;
	private Integer prioridadId;
	private String titulo;
	private String descripcion;
	private String emisionEstado;
	private String emisionFecha;
	private List<NotificacionDetalleDto> itemsNotificaciones;
	
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
	public Integer getPrioridadId() {
		return prioridadId;
	}
	public void setPrioridadId(Integer prioridadId) {
		this.prioridadId = prioridadId;
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
	public String getEmisionEstado() {
		return emisionEstado;
	}
	public void setEmisionEstado(String emisionEstado) {
		this.emisionEstado = emisionEstado;
	}
	public String getEmisionFecha() {
		return emisionFecha;
	}
	public void setEmisionFecha(String emisionFecha) {
		this.emisionFecha = emisionFecha;
	}
	public List<NotificacionDetalleDto> getItemsNotificaciones() {
		return itemsNotificaciones;
	}
	public void setItemsNotificaciones(List<NotificacionDetalleDto> itemsNotificaciones) {
		this.itemsNotificaciones = itemsNotificaciones;
	}
	
}
