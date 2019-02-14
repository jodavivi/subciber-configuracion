/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class CnfAplicacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String razonSocial;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}
