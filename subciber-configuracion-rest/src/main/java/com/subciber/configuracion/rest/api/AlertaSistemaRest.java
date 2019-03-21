/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.configuracion.dto.RequestDeleteObjectDto;

/**
 * @description Interface para mostrar las alertas por usuario
 * @author David Villanueva
 * @version 0.1, 05/03/2019
 * @update
 */
public interface AlertaSistemaRest {

	/**
	 * @param metodo para mostrar las alertas por usuario
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarAlertasSistema();
	
	/**
	 * @param metodo para eliminar el alerta del usuario
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarAlertaUsuario(RequestDeleteObjectDto request);
}
