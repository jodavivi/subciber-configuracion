/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class NotificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer estadoId;
	private String estado;
	private Integer prioridadId;
	private String prioridad;
	private String titulo;
	private String descripcion;
	private String emisionEstado;
	private String emisionFecha;
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
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getPrioridadId() {
		return prioridadId;
	}
	public void setPrioridadId(Integer prioridadId) {
		this.prioridadId = prioridadId;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
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
	
}
