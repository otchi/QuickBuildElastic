package com.edifixio.amine.config;

import java.util.Properties;

public abstract class Mapping  {
	protected  Properties config=new Properties();
	
	public Properties getConfig() {
		return config;
	}

	public void setConfig(Properties config) {
		this.config = config;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
