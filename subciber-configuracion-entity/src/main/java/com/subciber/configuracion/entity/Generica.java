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
@Table( schema="\"General\"",name="\"Generica\"")
public class Generica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "seq_generica", schema="\"General\"", sequenceName = "\"Generica_Id_seq\"", allocationSize = 1)
	@GeneratedValue(generator = "seq_generica")
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
	
	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;
	
	@Column(name="\"CodigoTabla\"")
	private String codigoTabla;
	
	@Column(name="\"DescripcionTabla\"")
	private String descripcionTabla ;
	
	@Column(name="\"Campo\"")
	private String campo = "";
	
	@Column(name="\"DescripcionCampo1\"")
	private String descripcionCampo1;
	
	@Column(name="\"DescripcionCampo2\"")
	private String descripcionCampo2;
	
	@Column(name="\"CodigoIntegracion\"")
	private String codigoIntegracion = "";
	
	@Column(name="\"Orden\"")
	private Integer orden;
	
	@Column(name="\"Fuente\"")
	private String fuente;
	
	@Column(name="\"Tipo\"")
	private String tipo;
	
	@Column(name="\"PadreId\"")
	private Integer padreId;
	
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
	public Integer getAplicacionId() {
		return aplicacionId;
	}
	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}
	public String getCodigoTabla() {
		return codigoTabla;
	}
	public void setCodigoTabla(String codigoTabla) {
		this.codigoTabla = codigoTabla;
	}
	public String getDescripcionTabla() {
		return descripcionTabla;
	}
	public void setDescripcionTabla(String descripcionTabla) {
		this.descripcionTabla = descripcionTabla;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getDescripcionCampo1() {
		return descripcionCampo1;
	}
	public void setDescripcionCampo1(String descripcionCampo1) {
		this.descripcionCampo1 = descripcionCampo1;
	}
	public String getDescripcionCampo2() {
		return descripcionCampo2;
	}
	public void setDescripcionCampo2(String descripcionCampo2) {
		this.descripcionCampo2 = descripcionCampo2;
	}
	public String getCodigoIntegracion() {
		return codigoIntegracion;
	}
	public void setCodigoIntegracion(String codigoIntegracion) {
		this.codigoIntegracion = codigoIntegracion;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Integer getPadreId() {
		return padreId;
	}
	public void setPadreId(Integer padreId) {
		this.padreId = padreId;
	}
	
}
