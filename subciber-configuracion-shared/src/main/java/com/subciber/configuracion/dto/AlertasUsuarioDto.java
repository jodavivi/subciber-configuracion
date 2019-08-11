/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class AlertasUsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer estadoId;
	private Integer prioridadId;
	private Integer alertaTipoId;
	private String titulo;
	private String descripcion;
	private Integer usuarioId;
	private String codigoPrioridad;
	private String estadoRecibido;
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
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getCodigoPrioridad() {
		return codigoPrioridad;
	}
	public void setCodigoPrioridad(String codigoPrioridad) {
		this.codigoPrioridad = codigoPrioridad;
	}
	public String getEstadoRecibido() {
		return estadoRecibido;
	}
	public void setEstadoRecibido(String estadoRecibido) {
		this.estadoRecibido = estadoRecibido;
	}
	@Override
	public String toString() {
		return "AlertasUsuarioDto [id=" + id + ", estadoId=" + estadoId + ", prioridadId=" + prioridadId
				+ ", alertaTipoId=" + alertaTipoId + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", usuarioId=" + usuarioId + ", codigoPrioridad=" + codigoPrioridad + ", estadoRecibido="
				+ estadoRecibido + "]";
	}
	
}
