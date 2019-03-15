/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.TablaGenericaDto;

/**
 * @description Interface para mostrar las tablas genericas
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
public interface TablaGenericaRest {

	/**
	 * @param metodo para mostrar las tablas genericas
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarTablaGenerica();
	
	/**
	 * @param metodo para mostrar las tablas genericas por filtro
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response consultarTablaGenericaFiltro();
	
	/**
	 * @param metodo para registrar tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response registrarTablaGenerica(TablaGenericaDto request);
	
	/**
	 * @param metodo para actualizar tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response actualizarTablaGenerica(TablaGenericaDto request);
	

	/**
	 * @param metodo para eliminar tabla generica
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarTablaGenerica(RequestDeleteObjectDto request);

}
