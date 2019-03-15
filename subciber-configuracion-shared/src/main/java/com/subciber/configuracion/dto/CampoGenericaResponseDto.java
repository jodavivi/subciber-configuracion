package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CampoGenericaResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<CampoGenericaDto> campoGenerica;

	public CampoGenericaResponseDto() {
	  super();
	  campoGenerica = new ArrayList<CampoGenericaDto>();
	}

	public List<CampoGenericaDto> getCampoGenerica() {
		return campoGenerica;
	}

	public void setCampoGenerica(List<CampoGenericaDto> campoGenerica) {
		this.campoGenerica = campoGenerica;
	}
	
}
