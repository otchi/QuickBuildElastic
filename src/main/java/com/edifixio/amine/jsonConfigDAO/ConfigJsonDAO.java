package com.edifixio.amine.jsonConfigDAO;

import com.edifixio.amine.config.Mapping;
import com.google.gson.JsonObject;

public abstract class ConfigJsonDAO <Type extends Mapping> {
	
	protected JsonObject jo;

	public ConfigJsonDAO(JsonObject jo) {
		
		this.jo = jo;
	}
	
	public abstract Type getMapping() throws ClassNotFoundException;

}
