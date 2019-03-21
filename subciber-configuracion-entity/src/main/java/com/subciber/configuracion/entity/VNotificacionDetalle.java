/**
 * 
 */
package com.subciber.configuracion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table( schema="\"Configuracion\"",name="\"VNotificacionDetalle\"")
public class VNotificacionDetalle implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"Estado\"")
	private String estado;
	
	@Column(name="\"PrioridadId\"")
	private Integer prioridadId;
	
	@Column(name="\"Prioridad\"")
	private String prioridad;
	
	@Column(name="\"Titulo\"")
	private String titulo;
	
	@Column(name="\"Descripcion\"")
	private String descripcion;
	
	@Column(name="\"EmisionEstado\"")
	private String emisionEstado;
	
	@Column(name="\"EmisionFecha\"")
	private String emisionFecha;
	
	@Column(name="\"Usuarios\"")
	private String usuarios;
	
	@Column(name="\"AlertaTipoId\"")
	private Integer alertaTipoId;
	
	@Column(name="\"AlertaTipo\"")
	private String alertaTipo;
	
	@Column(name="\"NotificacionId\"")
	private Integer notificacionId;

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

	public String getAlertaTipo() {
		return alertaTipo;
	}

	public void setAlertaTipo(String alertaTipo) {
		this.alertaTipo = alertaTipo;
	}

	public Integer getNotificacionId() {
		return notificacionId;
	}

	public void setNotificacionId(Integer notificacionId) {
		this.notificacionId = notificacionId;
	}
	
}
