package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.CampoGenericaDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para el mantenimiento de los campos genericos de la tabla
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
public interface CampoGenericaTxBusiness {

	/**
	 * @param metodo para registrar los campos de la tabla generica
	 * @return devuelve el Id del campo generica
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<Integer> registrarCampoGenerica(RequestGenericDto<CampoGenericaDto> request) throws BusinessException;

	/**
	 * @param metodo para actualizar el campo de la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto actualizarCampoGenerica(RequestGenericDto<CampoGenericaDto> request) throws BusinessException;

	/**
	 * @param metodo para eliminar el campo de la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarCampoGenerica(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;

}
