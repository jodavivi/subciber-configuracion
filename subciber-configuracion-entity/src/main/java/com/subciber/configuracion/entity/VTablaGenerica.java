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
@Table( schema="\"General\"",name="\"VTablaGenerica\"")
public class VTablaGenerica implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"Estado\"")
	private String estado;
	
	@Column(name="\"CodigoTabla\"")
	private String codigoTabla;
	
	@Column(name="\"DescripcionTabla\"")
	private String descripcionTabla;
	
	@Column(name="\"Orden\"")
	private Integer orden;
	
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
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
