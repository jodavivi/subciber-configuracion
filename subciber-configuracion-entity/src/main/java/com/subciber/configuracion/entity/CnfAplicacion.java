/**
 * 
 */
package com.subciber.configuracion.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table( schema="\"Configuracion\"",name="\"CnfAplicacion\"")
public class CnfAplicacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_gen",schema="\"Configuracion\"", sequenceName = "\"CnfAplicacion_Id_seq\"", allocationSize = 1)
	@GeneratedValue(generator = "seq_gen")
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"EmailNotificacion\"")
	private String emailNotificacion;

	@Column(name="\"EstadoId\"")
	private Integer estadoId;

	@Column(name="\"FechaCreacion\"")
	private LocalDateTime fechaCreacion;

	@Column(name="\"FechaModificacion\"")
	private LocalDateTime fechaModificacion;

	@Column(name="\"Logo\"")
	private String logo;

	@Column(name="\"NumeroIdentificacion\"")
	private String numeroIdentificacion;

	@Column(name="\"RazonSocial\"")
	private String razonSocial;

	@Column(name="\"TerminalCreacion\"")
	private String terminalCreacion;

	@Column(name="\"TerminalModificador\"")
	private String terminalModificador;

	@Column(name="\"TransaccionId\"")
	private String transaccionId;

	@Column(name="\"UsuarioCreador\"")
	private String usuarioCreador;

	@Column(name="\"UsuarioModificador\"")
	private String usuarioModificador;

	public CnfAplicacion() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmailNotificacion() {
		return emailNotificacion;
	}

	public void setEmailNotificacion(String emailNotificacion) {
		this.emailNotificacion = emailNotificacion;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTerminalCreacion() {
		return terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
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

	public String getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

}
