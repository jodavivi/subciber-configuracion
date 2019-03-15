package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.dto.RequestDeleteObjectDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para la tabla generica
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
public interface TablaGenericaTxBusiness {

	/**
	 * @param metodo para registrar la tabla generica
	 * @return devuelve el Id de la tabla generica creada
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<Integer> registrarTablaGenerica(RequestGenericDto<TablaGenericaDto> request) throws BusinessException;

	/**
	 * @param metodo para registrar la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto actualizarTablaGenerica(RequestGenericDto<TablaGenericaDto> request) throws BusinessException;

	/**
	 * @param metodo para eliminar la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarTablaGenerica(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;

}
