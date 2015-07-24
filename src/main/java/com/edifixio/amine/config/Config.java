package com.edifixio.amine.config;

import java.util.LinkedList;
import java.util.List;

import com.edifixio.amine.controller.Couple;

public abstract class Config<Type>  {
	protected  List<Couple<String, Type>> config=new LinkedList<Couple<String, Type>>();
	
	public List<Couple<String, Type>> getConfig() {
		return config;
	}

	public void setConfig(List<Couple<String, Type>> config) {
		this.config = config;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
