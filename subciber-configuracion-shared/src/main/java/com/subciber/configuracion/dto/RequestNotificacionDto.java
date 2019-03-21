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
public class RequestNotificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer total;
	private List<NotificacionDto> listNotificaciones;
	
	public RequestNotificacionDto() {
		super();
		listNotificaciones = new ArrayList<NotificacionDto>();
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<NotificacionDto> getListNotificaciones() {
		return listNotificaciones;
	}

	public void setListNotificaciones(List<NotificacionDto> listNotificaciones) {
		this.listNotificaciones = listNotificaciones;
	}
	
	 	 
}
