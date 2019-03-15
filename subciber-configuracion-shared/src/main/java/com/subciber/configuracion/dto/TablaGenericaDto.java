package com.subciber.configuracion.dto;

import java.io.Serializable;

public class TablaGenericaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer estadoId;
	private String estado;
	private String codigoTabla;
	private String descripcionTabla;
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
