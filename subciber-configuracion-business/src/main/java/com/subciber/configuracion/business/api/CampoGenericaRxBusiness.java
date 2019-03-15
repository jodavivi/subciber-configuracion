package com.subciber.configuracion.business.api;

import com.subciber.configuracion.dto.CampoGenericaFiltroDto;
import com.subciber.configuracion.dto.CampoGenericaResponseDto;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.dto.ResponseGenericDto;
import com.subciber.configuracion.exception.BusinessException;

/**
 * @description Interface para los campos generica
 * @author David Villanueva
 * @version 0.1, 12/03/2019
 * @update
 */
public interface CampoGenericaRxBusiness {

	/**
	 * @param metodo para consultar los campos de una tabla generica
	 * @return devuelve informacion de los campos de una tabla generica
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<CampoGenericaResponseDto> consultarCampoGenerica(RequestGenericDto<CampoGenericaFiltroDto> request) throws BusinessException;

}
