package com.subciber.configuracion.dto;

import java.io.Serializable;

public class CargaArchivoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer tipoArchivo;

	public Integer getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(Integer tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	@Override
	public String toString() {
		return "CargaArchivoDto [tipoArchivo=" + tipoArchivo + "]";
	}
	

}
