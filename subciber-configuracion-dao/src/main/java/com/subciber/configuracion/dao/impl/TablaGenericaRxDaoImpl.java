/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.dao.api.TablaGenericaRxDao;
import com.subciber.configuracion.dto.TablaGenericaFiltroDto;
import com.subciber.configuracion.entity.VTablaGenerica;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author jose david villanueva villalobos
 * @Creacion 05 mar. 2019
 * @Update
 * 
 */
@Stateless
public class TablaGenericaRxDaoImpl  extends BaseJPADao<VTablaGenerica> implements TablaGenericaRxDao, Serializable {

	@Inject
    private MessageProvider messageProvider;
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	@Override
	public List<VTablaGenerica> consultarTablaGenerica(TablaGenericaFiltroDto request) throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VTablaGenerica> query = null;
		List<VTablaGenerica> resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("t ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" t ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("1 = 1 ");
			
			if(request.getTablaCodigo() != null) {
				jpqlWhere.append("and t.codigoTabla = :tablaCodigo ");
			}
			if(request.getEstadoId() != null) {
				jpqlWhere.append("and t.estadoId = :estadoId ");
			}
			if(request.getTablaId() != null) {
				jpqlWhere.append("and t.tablaId = :tablaId ");
			}

			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VTablaGenerica.class);
			
			if(request.getTablaCodigo() != null) {
				query.setParameter("tablaCodigo", request.getTablaCodigo()); 
			}
			if(request.getEstadoId() != null) {
				query.setParameter("estadoId", request.getEstadoId()); 
			}
			if(request.getTablaId() != null) {
				query.setParameter("tablaId", request.getTablaId()); 
			}
			
			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }else {
				  error = e.getCause().toString();
			  }
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));

		}
		return resultado;
	} 
}
