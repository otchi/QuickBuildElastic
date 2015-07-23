package com.edifixio.amine.config;

import java.util.HashSet;
import java.util.Set;

import com.edifixio.amine.controller.Couple;

public abstract class Config<Type>  {
	private  Set<Couple<String, Type>> config=new HashSet<Couple<String, Type>>();
	
	public Set<Couple<String, Type>> getConfig() {
		return config;
	}

	public void setConfig(Set<Couple<String, Type>> config) {
		this.config = config;
	}

	
}
