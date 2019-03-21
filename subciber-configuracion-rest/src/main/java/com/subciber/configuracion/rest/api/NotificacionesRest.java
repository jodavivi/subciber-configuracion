/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.configuracion.dto.CrearNotificacionDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;

/**
 * @description Interface para el mantenimiento de las notificaciones del sistema
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
public interface NotificacionesRest {

	/**
	 * @param metodo para mostrar las notificaciones del sistema
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarNotificaciones();
	
	/**
	 * @param metodo para buscar una notificacion
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response mostrarNotificacion();
	
	/**
	 * @param metodo para crear una notificacion
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response crearNotificacion(CrearNotificacionDto request);
	
	/**
	 * @param metodo para actualizar una notificacion
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response actualizarNotificacion(CrearNotificacionDto request);
	
	/**
	 * @param metodo para eliminar una notificacion
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarNotificacion(RequestDeleteObjectDto request);
}
