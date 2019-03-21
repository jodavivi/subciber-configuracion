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
public class NotificacionDetalleResponseDto extends NotificacionDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<NotificacionDetalleDto> itemDetalle;
	
	public NotificacionDetalleResponseDto() {
		super();
		itemDetalle = new ArrayList<NotificacionDetalleDto>();
	}

	public List<NotificacionDetalleDto> getItemDetalle() {
		return itemDetalle;
	}

	public void setItemDetalle(List<NotificacionDetalleDto> itemDetalle) {
		this.itemDetalle = itemDetalle;
	}
	
	
}
