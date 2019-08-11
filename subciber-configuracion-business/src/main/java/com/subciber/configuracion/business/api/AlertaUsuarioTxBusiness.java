package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.RequestRegistroAlertaSistemaDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para el mantenimiento de las alertas del sistema
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update 10/08/2019 - David Villanueva
 */
public interface AlertaUsuarioTxBusiness {

	/**
	 * @param metodo para registrar el Alerta de usuario
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto registrarAlertaUsuario(RequestGenericDto<RequestRegistroAlertaSistemaDto> request) throws BusinessException;

	
	/**
	 * @param metodo para eliminar el Alerta de usuario
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarAlertaUsuario(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;

}
