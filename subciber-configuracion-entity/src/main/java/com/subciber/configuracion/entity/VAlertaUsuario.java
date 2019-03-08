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
@Table( schema="\"Configuracion\"",name="\"VAlertaUsuario\"")
public class VAlertaUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"Titulo\"")
	private String titulo;
	
	@Column(name="\"Descripcion\"")
	private String descripcion;
	
	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;
	
	@Column(name="\"Codigo\"")
	private String codigo;
	
	@Column(name="\"Usuario\"")
	private String usuario;
	
	@Column(name="\"EstadoRecibido\"")
	private String estadoRecibido;
	
	@Column(name="\"PrioridadId\"")
	private Integer prioridadId;
	
	@Column(name="\"Prioridad\"")
	private String prioridad;
	
	@Column(name="\"AlertaTipoId\"")
	private Integer alertaTipoId;
	
	@Column(name="\"AlertaTipo\"")
	private String alertaTipo;
	
	@Column(name="\"FechaCreacion\"")
	private LocalDateTime fechaCreacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEstadoRecibido() {
		return estadoRecibido;
	}

	public void setEstadoRecibido(String estadoRecibido) {
		this.estadoRecibido = estadoRecibido;
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

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
