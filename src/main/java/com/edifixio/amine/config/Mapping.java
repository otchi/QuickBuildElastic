package com.edifixio.amine.config;

import java.util.Map;

public class  Mapping<Type> extends Config<Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<?> classe;
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
	public Class<?> getClasse() {
		return classe;
	}
	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}


}
