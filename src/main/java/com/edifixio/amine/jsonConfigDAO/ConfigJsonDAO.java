package com.edifixio.amine.jsonConfigDAO;

import java.util.HashMap;

import com.google.gson.JsonObject;

public abstract class ConfigJsonDAO <Type extends HashMap<String, ?>> {

	protected JsonObject jo;

	public ConfigJsonDAO(JsonObject jo) {
		
		this.jo = jo;
	}
	
	public abstract Type getMapping() throws ClassNotFoundException;

}
