/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;

import com.subciber.configuracion.base.dto.AuditRequestDto;

/**
 * @author josep
 *
 */
public class RequestGenericDto<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditRequestDto auditRequest;
	private T objectRequest;
	
	public RequestGenericDto() {
		super();
		auditRequest = new AuditRequestDto();
	}

	public AuditRequestDto getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequestDto auditRequest) {
		this.auditRequest = auditRequest;
	}

	public T getObjectRequest() {
		return objectRequest;
	}

	public void setObjectRequest(T objectRequest) {
		this.objectRequest = objectRequest;
	}

}
