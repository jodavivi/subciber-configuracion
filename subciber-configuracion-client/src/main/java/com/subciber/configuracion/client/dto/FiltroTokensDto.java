package com.subciber.configuracion.client.dto;

import java.io.Serializable;

public class FiltroTokensDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String tokens; 
	
	public String getTokens() {
		return tokens;
	}
	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
 
	
}