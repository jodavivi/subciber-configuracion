/**
 * 
 */
package com.subciber.configuracion.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table( schema="\"Configuracion\"",name="\"VNotificacion\"")
public class VNotificacion implements Serializable {
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
	private LocalDateTime emisionFecha;

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

	public LocalDateTime getEmisionFecha() {
		return emisionFecha;
	}

	public void setEmisionFecha(LocalDateTime emisionFecha) {
		this.emisionFecha = emisionFecha;
	}
	
}
