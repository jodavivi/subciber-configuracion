/**
 * 
 */
package com.subciber.configuracion.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.configuracion.business.dto.EnvioCorreoDto;

/**
 * @description Interface para el envio de correo
 * @author David Villanueva
 * @version 0.1, 15/02/2019
 * @update
 */
public interface EnvioCorreoRest {
	/**
	 * @param metodo para enviar correo
	 * @return devuelve AuditResponseDto
	 * @throws 
	 */
	public abstract Response enviarCorreo(EnvioCorreoDto request);
	
	/**
	 * @param metodo para enviar correo sin Autenticacion
	 * @return devuelve AuditResponseDto
	 * @throws 
	 */
	public abstract Response enviarCorreoSinAutenticacion(EnvioCorreoDto request);
	
}
