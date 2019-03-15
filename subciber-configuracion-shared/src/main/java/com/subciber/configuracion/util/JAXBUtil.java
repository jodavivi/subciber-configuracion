package com.subciber.configuracion.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.xmlbeans.XmlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
 

/**
 * @description Class to map the inputs and outputs in XML format
 * @author David Villanueva
 * @version 0.1, 02/11/2016
 * @update
 */
public class JAXBUtil {
	
	final static Logger logger = LoggerFactory.getLogger(JAXBUtil.class);
	
	@SuppressWarnings("rawtypes")
	private static Map<Class, JAXBContext> mapContexts = new HashMap<Class, JAXBContext>();
	private static Map<String, JAXBContext> mapContextsGenerics = new HashMap<String, JAXBContext>();
	
	/**
	 * Returns the JAXBContext
	 * @param clas The Class for the JAXBContext
	 * @return The JAXBContext
	 */
	private static JAXBContext obtainJaxBContextFromClass(Class<?> clazz) {
		
		JAXBContext context;
		context = mapContexts.get(clazz);
		
		if (context == null) {
			try {
				/* JAXBContext.newInstance is very expensive to improve use mapContexts  */
				context = JAXBContext.newInstance(clazz);
				mapContexts.put(clazz, context);
			} catch (Exception e) {
				logger.error("The creation of a JAXB context failed : {}", e.getMessage());
			}
		}
		return context;
	}
	
	/**
	 * Returns the JAXBContext
	 * @param clazz The Class for the JAXBContext
	 * @param genericClass The generic class
	 * @return The JAXBContext
	 */
	private static JAXBContext obtainJaxBContextFromGenericClass(Class<?> clazz, Class<?> genericClass) {
		
		StringBuilder keyContextGeneric = null;
		JAXBContext context = null;
		
		keyContextGeneric = new StringBuilder(genericClass.getSimpleName());
		keyContextGeneric.append("-");
		keyContextGeneric.append(clazz.getSimpleName());
		
		context = mapContextsGenerics.get(keyContextGeneric.toString());
		
		if (context == null) {
			try {
				/* JAXBContext.newInstance is very expensive to improve use mapContexts  */
				context = JAXBContext.newInstance(genericClass, clazz);
				mapContextsGenerics.put(keyContextGeneric.toString(), context);
			} catch (Exception e) {
				logger.error("The creation of a JAXB context failed : {}", e.getMessage());
			}
		}
		return context;
	}
	
	/*
	public static String jaxBToXmlText(Object objJaxB) {
		
		String commandoRequestEnXml = null;
		JAXBContext context;
		
		try {
			context = obtainJaxBContextFromClass(objJaxB.getClass()); //se hace esto para mejorar performance.
			Marshaller marshaller = context.createMarshaller();
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal(objJaxB, xmlWriter);
			
			XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());
			commandoRequestEnXml = xmlObj.toString();
			
		} catch (Exception e) {
			logger.error("Error parsing object to xml:", e);
		}
		
		return commandoRequestEnXml;
	}
	*/
	
	/**
	 * @param anyObject The Java Object
	 * @return The string with XML Format
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String anyObjectToXmlText(Object anyObject) {
		
		String commandoRequestEnXml = null;
		JAXBContext context;
		
		try {
			/* The method obtainJaxBContextFromClass improves performance */
			context = obtainJaxBContextFromClass(anyObject.getClass());
			Marshaller marshaller = context.createMarshaller();
			
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal(new JAXBElement(new QName("", anyObject.getClass().getSimpleName()), anyObject.getClass(), anyObject), xmlWriter);
			
			XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());
			commandoRequestEnXml = xmlObj.toString();
		} catch (Exception e) {
			logger.error("Error parseando object to xml:", e);
		}
		
		return commandoRequestEnXml;
	}
	
	/**
	 * @param anyObject The Java Object
	 * @param genericClass The generic Class
	 * @return The string with XML Format
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String anyObjectToXmlText(Object anyObject, Class<?> genericClass) {
		
		String commandoRequestEnXml = null;
		JAXBContext context;
		
		try {
			/* The method obtainJaxBContextFromClass improves performance */
			context = obtainJaxBContextFromGenericClass(anyObject.getClass(), genericClass);
			Marshaller marshaller = context.createMarshaller();
			
			StringWriter xmlWriter = new StringWriter();
			marshaller.marshal(new JAXBElement(new QName("", anyObject.getClass().getSimpleName()), genericClass, anyObject), xmlWriter);
			
			XmlObject xmlObj = XmlObject.Factory.parse(xmlWriter.toString());
			commandoRequestEnXml = xmlObj.toString();
		} catch (Exception e) {
			logger.error("Error parseando object to xml:", e);
		}
		
		return commandoRequestEnXml;
	}
	
	/*
	@SuppressWarnings("rawtypes")
	public static Object xmlTextToJaxB(String xmlText, Class clas) {
		
		JAXBContext context;
		Object object = null;
		
		try {
			context = obtainJaxBContextFromClass(clas); //se hace esto para mejorar performance.
			Unmarshaller um = context.createUnmarshaller();
			
			InputStream is = new ByteArrayInputStream(xmlText.getBytes("UTF-8"));
			object = um.unmarshal(is);
			
		} catch (Exception e) {
			logger.error("Error unmarshalling xmlObject: {}. Detalle Error: {}", xmlText, e);
		}
		return object;
	}
	*/
	
	/**
	 * Returns string with JSON format from object fields
	 * @param obj The object
	 * @return The JSON string
	 */
	public static String log(Object obj) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
}