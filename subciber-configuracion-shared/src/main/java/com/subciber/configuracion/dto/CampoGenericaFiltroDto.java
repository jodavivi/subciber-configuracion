package com.subciber.configuracion.dto;

import java.io.Serializable;

public class CampoGenericaFiltroDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoTabla;
	private Integer campoId;
	private Integer estadoId;
	
	public String getCodigoTabla() {
		return codigoTabla;
	}
	public void setCodigoTabla(String codigoTabla) {
		this.codigoTabla = codigoTabla;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Integer getCampoId() {
		return campoId;
	}
	public void setCampoId(Integer campoId) {
		this.campoId = campoId;
	}
	
}
