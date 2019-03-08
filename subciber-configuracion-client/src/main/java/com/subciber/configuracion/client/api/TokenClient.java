package com.subciber.configuracion.client.api;

import com.subciber.configuracion.client.dto.FiltroTokensDto;
import com.subciber.configuracion.client.dto.ResponseValidarTokens;
import com.subciber.configuracion.dto.RequestGenericDto;
import com.subciber.configuracion.exception.ClientException;

public interface TokenClient {

	/**
	 * @param metodo para actualizar el token
	 * @return devuelve el nuevo token generado
	 * @throws ClientException
	 */
	public abstract ResponseValidarTokens validarTokens(RequestGenericDto<FiltroTokensDto> tokens) throws ClientException;
}
