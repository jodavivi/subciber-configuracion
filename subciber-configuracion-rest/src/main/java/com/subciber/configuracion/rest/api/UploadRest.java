/**
 * 
 */
package com.subciber.configuracion.rest.api;

import java.io.InputStream;

import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * @description Interface para el manejo de archivos
 * @author David Villanueva
 * @version 0.1, 16/07/2019
 * @update
 */
public interface UploadRest {
	
	/**
	 * @param metodo para subir el archivo
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response registrarArchivo(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail);
	

}
