/**
 * 
 */
package com.subciber.configuracion.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.configuracion.business.api.AplicacionBusiness;
import com.subciber.configuracion.dao.api.AplicacionDao;
import com.subciber.configuracion.dto.CnfAplicacionDto;
import com.subciber.configuracion.entity.CnfAplicacion;
import com.subciber.configuracion.exception.BusinessException;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @description implementacion de la interface AplicacionBusiness
 * @author David Villanueva
 * @version 0.1, 14/02/2019
 * @update
 */
@Dependent
public class AplicacionBusinessImpl implements AplicacionBusiness, Serializable {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	
	@Inject
	private AplicacionDao aplicacionDao2;
	
	@Override
	public CnfAplicacionDto get(Integer request) throws  BusinessException  {
		CnfAplicacion app;
		CnfAplicacionDto ss = new CnfAplicacionDto();
		
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			app = aplicacionDao2.get(request);
			if(app == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Aplicacion"));
			}
			
			ss.setCodigo(app.getId());
			ss.setRazonSocial(app.getRazonSocial());
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch(Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		return ss;
	}

}
