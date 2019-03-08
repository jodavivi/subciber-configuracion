package com.subciber.configuracion.client.dto;

import java.io.Serializable;

import com.subciber.configuracion.base.dto.AuditResponseDto;

public class ResponseValidarTokens implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditResponseDto auditResponse;
	private EstructuraTokensDto objectResponse;
	
	public ResponseValidarTokens(){
		super();
		auditResponse = new AuditResponseDto();
	}
	
	public AuditResponseDto getAuditResponse() {
		return auditResponse;
	}
	public void setAuditResponse(AuditResponseDto auditResponse) {
		this.auditResponse = auditResponse;
	}
	public EstructuraTokensDto getObjectResponse() {
		return objectResponse;
	}
	public void setObjectResponse(EstructuraTokensDto objectResponse) {
		this.objectResponse = objectResponse;
	}
	@Override
	public String toString() {
		return "ResponseValidarTokens [auditResponse=" + auditResponse + ", objectResponse=" + objectResponse + "]";
	}
	
}
