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
