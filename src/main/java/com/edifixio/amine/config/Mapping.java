package com.edifixio.amine.config;

import java.util.HashMap;

public abstract class  Mapping<Type> extends HashMap<String, Type> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Class<?> classe;
	
	public Class<?> getClasse() {
		return classe;
	}
	public void setClasse(Class<?> classe) {
		this.classe = classe;
	}


}
