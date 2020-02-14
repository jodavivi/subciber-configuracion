package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

/**
 * @description Interface para configurar parametros de la maestra
 * @author David Villanueva
 * @version 0.1, 14/02/2020
 * @update
 */
public interface ConfiguracionGenericaRest {

	/**
	 * @param metodo para activar el periodo actual
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response activarPeriodoActual();
	
}
