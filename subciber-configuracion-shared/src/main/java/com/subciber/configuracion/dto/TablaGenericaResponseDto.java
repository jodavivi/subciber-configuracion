package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TablaGenericaResponseDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<TablaGenericaDto> tablaGenerica;

	public TablaGenericaResponseDto() {
	  super();
	  tablaGenerica = new ArrayList<TablaGenericaDto>();
	}
	public List<TablaGenericaDto> getTablaGenerica() {
		return tablaGenerica;
	}

	public void setTablaGenerica(List<TablaGenericaDto> tablaGenerica) {
		this.tablaGenerica = tablaGenerica;
	}
	
}
