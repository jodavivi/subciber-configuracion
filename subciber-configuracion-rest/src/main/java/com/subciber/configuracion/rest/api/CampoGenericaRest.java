/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;

/**
 * @description Interface para mostrar los campos de la tabla generica
 * @author David Villanueva
 * @version 0.1, 12/03/2019
 * @update
 */
public interface CampoGenericaRest {

	/**
	 * @param metodo para mostrar los campos de tablas genericas
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarCampoGenerica();
	
	/**
	 * @param metodo para mostrar el detalle del campo de tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarDetalleCampoGenerica();
	
	/**
	 * @param metodo para registrar los campos de la tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response registrarCampoGenerica(CampoGenericaDto request);
	
	/**
	 * @param metodo para actualizar los campos de la tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response actualizarCampoGenerica(CampoGenericaDto request);
	

	/**
	 * @param metodo para eliminar los campos de la tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarCampoGenerica(RequestDeleteObjectDto request);

	
}
