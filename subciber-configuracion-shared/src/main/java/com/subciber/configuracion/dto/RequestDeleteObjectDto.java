/**
 * 
 */
package com.subciber.configuracion.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author josep
 *
 */
public class RequestDeleteObjectDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> items;

	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}

}
