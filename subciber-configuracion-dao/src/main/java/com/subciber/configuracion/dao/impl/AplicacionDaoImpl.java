/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import com.subciber.configuracion.base.dao.GenericaJPADaoImpl;
import com.subciber.configuracion.dao.api.AplicacionDao;
import com.subciber.configuracion.entity.CnfAplicacion;

/**
 * @author josep
 *
 */
@Dependent
public class AplicacionDaoImpl  extends GenericaJPADaoImpl<CnfAplicacion>  implements AplicacionDao , Serializable {

	private static final long serialVersionUID = 1L;

	}
