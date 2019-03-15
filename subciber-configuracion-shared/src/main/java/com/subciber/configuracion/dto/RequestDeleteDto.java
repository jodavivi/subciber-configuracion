/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.subciber.configuracion.base.dto.AuditRequestDto;

/**
 * @author josep
 *
 */
public class RequestDeleteDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditRequestDto auditRequest;
	private List<Integer> items;
	
	public RequestDeleteDto() {
		super();
		auditRequest = new AuditRequestDto();
		items 		 = new ArrayList<Integer>();
	}

	public AuditRequestDto getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequestDto auditRequest) {
		this.auditRequest = auditRequest;
	}

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

}
