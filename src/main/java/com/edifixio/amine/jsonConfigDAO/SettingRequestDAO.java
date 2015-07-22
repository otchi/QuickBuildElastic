package com.edifixio.amine.jsonConfigDAO;

import java.util.HashMap;

import com.google.gson.JsonObject;

public class SettingRequestDAO extends JsonConfDAO<HashMap<String,String>>{

	public SettingRequestDAO(JsonObject jo) {
		super(jo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HashMap<String, String> getMapping() {
		// TODO Auto-generated method stub
		return null;
	}

}
