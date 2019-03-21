package com.subciber.configuracion.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table( schema="\"Configuracion\"",name="\"NotificacionUsuario\"")
public class NotificacionUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_notificacionUsuario", schema="\"Configuracion\"", sequenceName = "\"NotificacionUsuario_Id_seq\"", allocationSize = 1)
	@GeneratedValue(generator = "seq_notificacionUsuario")
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"UsuarioCreador\"")
	private String usuarioCreador;
	
	@Column(name="\"FechaCreacion\"")
	private LocalDateTime fechaCreacion; 
	
	@Column(name="\"TerminalCreacion\"")
	private String terminalCreacion;
	
	@Column(name="\"UsuarioModificador\"")
	private String usuarioModificador;
	
	@Column(name="\"FechaModificacion\"")
	private LocalDateTime fechaModificacion;
	
	@Column(name="\"TerminalModificador\"")
	private String terminalModificador;
	
	@Column(name="\"TransaccionId\"")
	private String transaccionId = "";
	
	@Column(name="\"NotificacionId\"")
	private Integer notificacionId;
	
	@Column(name="\"Usuarios\"")
	private String usuarios;
	
	@Column(name="\"AlertaTipoId\"")
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

	public String getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTerminalCreacion() {
		return terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
	}

	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getTerminalModificador() {
		return terminalModificador;
	}

	public void setTerminalModificador(String terminalModificador) {
		this.terminalModificador = terminalModificador;
	}

	public String getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
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
