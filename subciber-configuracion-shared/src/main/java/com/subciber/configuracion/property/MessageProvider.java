/**
 * 
 */
package com.subciber.configuracion.property;

import com.byteslounge.cdi.annotation.Property;
import javax.enterprise.context.Dependent;

/**
 * @author josep
 *
 */
@Dependent
public class MessageProvider {
	

	@Property(value = "LOG.MESSAGE.INIT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeInicio;
	@Property(value = "LOG.MESSAGE.INPUT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeInp;
	@Property(value = "LOG.MESSAGE.SERVICE.INPUT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeServicioInp;
	@Property(value = "LOG.MESSAGE.DATA.INPUT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeDataInp;
	@Property(value = "LOG.MESSAGE.OUTPUT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeOut;
	@Property(value = "LOG.MESSAGE.SERVICE.OUTPUT", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeServiceOut;
	@Property(value = "LOG.MESSAGE.SUCCESS", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeExito;
	@Property(value = "LOG.MESSAGE.EXCEPTION", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeError;
	@Property(value = "LOG.MESSAGE.TIME", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeTime;
	@Property(value = "LOG.MESSAGE.END", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeEnd;
	@Property(value = "LOG.MESSAGE.QUERY", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeQuery;
	@Property(value = "LOG.MESSAGE.QUERY.SUCCESS", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeDataExito;
	@Property(value = "LOG.MESSAGE.RESPONSE_NULL", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String logMensajeNull;
	
	@Property(value = "codigo.exito", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoExito;
	
	@Property(value = "mensaje.exito", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeExito;
	
	@Property(value = "codigo.error.idf2", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf2;
	
	@Property(value = "mensaje.error.idf2", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf2;
	
	@Property(value = "codigo.error.idf3", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf3;
	
	@Property(value = "mensaje.error.idf3", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf3;

	@Property(value = "codigo.error.idf4", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf4;
	
	@Property(value = "mensaje.error.idf4", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf4;

	@Property(value = "codigo.error.idf5", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf5;
	
	@Property(value = "mensaje.error.idf5", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf5;
	
	@Property(value = "codigo.error.idf6", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf6;
	
	@Property(value = "mensaje.error.idf6", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf6;
	
	@Property(value = "codigo.error.idf7", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdf7;
	
	@Property(value = "mensaje.error.idf7", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdf7;
	
	
	@Property(value = "codigo.error.idt1", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdt1;
	
	@Property(value = "mensaje.error.idt1", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdt1;
	
	@Property(value = "codigo.error.idt2", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdt2;
	
	@Property(value = "mensaje.error.idt2", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdt2;
	
	@Property(value = "codigo.error.idt3", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdt3;
	
	@Property(value = "mensaje.error.idt3", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdt3;
	
	@Property(value = "codigo.error.idt4", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public Integer codigoErrorIdt4;
	
	@Property(value = "mensaje.error.idt4", resourceBundleBaseName = "com.subciber.configuracion.i18n.mensajes")
	public String mensajeErrorIdt4;

}
