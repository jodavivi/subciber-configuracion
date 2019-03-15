package com.subciber.configuracion.dto;

import java.io.Serializable;

public class TablaGenericaFiltroDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer tablaId;
	private String tablaCodigo;
	private Integer estadoId;
	
	public Integer getTablaId() {
		return tablaId;
	}
	public void setTablaId(Integer tablaId) {
		this.tablaId = tablaId;
	}
	public String getTablaCodigo() {
		return tablaCodigo;
	}
	public void setTablaCodigo(String tablaCodigo) {
		this.tablaCodigo = tablaCodigo;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	
}
