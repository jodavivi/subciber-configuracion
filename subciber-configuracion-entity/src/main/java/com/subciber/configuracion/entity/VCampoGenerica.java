package com.subciber.configuracion.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( schema="\"General\"",name="\"VCampoGenerica\"")
public class VCampoGenerica implements Serializable {

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
	
	@Column(name="\"Campo\"")
	private String campo;
	
	@Column(name="\"DescripcionCampo1\"")
	private String descripcionCampo1;
	
	@Column(name="\"DescripcionCampo2\"")
	private String descripcionCampo2;
	
	@Column(name="\"CodigoIntegracion\"")
	private String codigoIntegracion;
	
	@Column(name="\"PadreId\"")
	private Integer padreId;
	
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

	public Integer getPadreId() {
		return padreId;
	}

	public void setPadreId(Integer padreId) {
		this.padreId = padreId;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
}
