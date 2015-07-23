package com.edifixio.amine.config;

import java.util.Map;

public class  Mapping<Type> extends Config<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<?> mapClass;
	private Map<String,String> alias;
	
	public Map<String, String> getAlias() {
		return alias;
	}
	public void setAlias(Map<String, String> alias) {
		this.alias = alias;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Class<?> getMapClass() {
		return mapClass;
	}
	public void setMapClass(Class<?> mapClass) {
		this.mapClass = mapClass;
	}


}
