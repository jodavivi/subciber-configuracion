package com.subciber.configuracion.business.api;

import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.dto.TablaGenericaFiltroDto;
import com.subciber.configuracion.dto.TablaGenericaResponseDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para la tabla generica
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
public interface TablaGenericaRxBusiness {

	/**
	 * @param metodo para consultar la tabla generica
	 * @return devuelve informacion de la tabla generica
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<TablaGenericaResponseDto> consultarTablaGenerica(RequestGenericDto<TablaGenericaFiltroDto> request) throws BusinessException;

}
