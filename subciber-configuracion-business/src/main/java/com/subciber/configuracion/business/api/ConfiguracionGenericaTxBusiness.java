package com.subciber.configuracion.business.api;

import com.subciber.configuracion.base.dto.AuditRequestDto;
import com.subciber.configuracion.base.dto.AuditResponseDto;
import com.subciber.configuracion.exception.BusinessException;

public interface ConfiguracionGenericaTxBusiness {

	/**
	 * @param metodo para validar el periodo actual
	 * @return 
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto validarPeriodoActual(AuditRequestDto request) throws BusinessException;

}
