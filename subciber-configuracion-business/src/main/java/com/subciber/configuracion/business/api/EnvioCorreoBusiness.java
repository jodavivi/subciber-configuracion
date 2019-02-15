/**
 * 
 */
package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.business.dto.EnvioCorreoDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para el envio de correo
 * @author David Villanueva
 * @version 0.1, 14/02/2019
 * @update
 */
public interface EnvioCorreoBusiness {

	/**
	 * @param metodo para enviar correos
	 * @return devuelve dato generico de auditoria
	 * @throws BusinessException
	 */
	public AuditResponseDto enviarCorreo(RequestGenericDto<EnvioCorreoDto> request)  throws BusinessException;
}
