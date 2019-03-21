/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

/**
 * @description Interface para el mantenimiento del detalle de la notificacion
 * @author David Villanueva
 * @version 0.1, 16/03/2019
 * @update
 */
public interface NotificacionDetalleRest {

	/**
	 * @param metodo para mostrar el detalle de la notificacion
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarNotificacionDetalle();
	
	 
}
