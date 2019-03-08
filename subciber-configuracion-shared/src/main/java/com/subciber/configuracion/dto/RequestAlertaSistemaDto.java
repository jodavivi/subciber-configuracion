/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author josep
 *
 */
public class RequestAlertaSistemaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer numAlertas;
	private List<AlertasSistemaDto> alertasSistema;
	
	public RequestAlertaSistemaDto() {
		super();
		alertasSistema = new ArrayList<AlertasSistemaDto>();
	}
	
	public Integer getNumAlertas() {
		return numAlertas;
	}
	public void setNumAlertas(Integer numAlertas) {
		this.numAlertas = numAlertas;
	}
	public List<AlertasSistemaDto> getAlertasSistema() {
		return alertasSistema;
	}
	public void setAlertasSistema(List<AlertasSistemaDto> alertasSistema) {
		this.alertasSistema = alertasSistema;
	}
	 	 
}
