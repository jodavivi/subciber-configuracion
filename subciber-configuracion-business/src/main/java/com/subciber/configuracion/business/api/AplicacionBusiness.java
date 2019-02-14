package com.subciber.configuracion.business.api;

import com.subciber.configuracion.dto.CnfAplicacionDto;
import com.subciber.configuracion.exception.BusinessException;

public interface AplicacionBusiness {
	
	public CnfAplicacionDto get(Integer request) throws BusinessException;

}
