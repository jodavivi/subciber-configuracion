/**
 * 
 */
package com.subciber.configuracion.dao.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.configuracion.base.dao.BaseJPADao;
import com.subciber.configuracion.dao.api.CampoGenericaRxDao;
import com.subciber.configuracion.dto.CampoGenericaFiltroDto;
import com.subciber.configuracion.entity.VCampoGenerica;
import com.subciber.configuracion.exception.DaoException;
import com.subciber.configuracion.property.MessageProvider;

/**
 * @author jose david villanueva villalobos
 * @Creacion 05 mar. 2019
 * @Update
 * 
 */
@Stateless
public class CampoGenericaRxDaoImpl  extends BaseJPADao<VCampoGenerica> implements CampoGenericaRxDao, Serializable {

	@Inject
    private MessageProvider messageProvider;
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	@Override
	public  List<VCampoGenerica> consultarCampoGenerica(CampoGenericaFiltroDto request)  throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VCampoGenerica> query = null;
		List<VCampoGenerica> resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("c ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" c ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("1 = 1 ");
			
			if(request.getCodigoTabla() != null) {
				jpqlWhere.append("and c.codigoTabla = :tablaCodigo ");
			}
			if(request.getEstadoId() != null) {
				jpqlWhere.append("and c.estadoId = :estadoId ");
			}
			if(request.getCampoId() != null) {
				jpqlWhere.append("and c.id = :campoId ");
			}
			
			if(request.getNotId() != null) {
				jpqlWhere.append("and c.estadoId <> :notId ");
			}

			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VCampoGenerica.class);
			
			if(request.getCodigoTabla() != null) {
				query.setParameter("tablaCodigo", request.getCodigoTabla()); 
			}
			if(request.getEstadoId() != null) {
				query.setParameter("estadoId", request.getEstadoId()); 
			}
			if(request.getCampoId() != null) {
				query.setParameter("campoId", request.getCampoId()); 
			}
			if(request.getNotId() != null) {
				query.setParameter("notId", request.getNotId()); 
			}
			
			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return resultado;
	} 
}
