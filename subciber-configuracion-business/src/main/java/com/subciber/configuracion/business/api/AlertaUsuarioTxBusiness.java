package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para el mantenimiento de los campos genericos de la tabla
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
public interface AlertaUsuarioTxBusiness {

	/**
	 * @param metodo para eliminar el Alerta de usuario
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarAlertaUsuario(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;

}
