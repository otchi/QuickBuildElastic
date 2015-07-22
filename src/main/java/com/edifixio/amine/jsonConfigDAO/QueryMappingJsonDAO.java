package com.edifixio.amine.jsonConfigDAO;

import java.util.List;

import com.edifixio.amine.config.Mapping;
import com.google.gson.JsonObject;

public class QueryMappingJsonDAO extends  JsonConfDAO<Mapping<List<String>>>{

	public QueryMappingJsonDAO(JsonObject jo) {
		super(jo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Mapping<List<String>> getMapping() {
		// TODO Auto-generated method stub
		return null;
	}

}
