package com.edifixio.amine.jsonConfigDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.edifixio.amine.config.ResponseMapping;
import com.edifixio.jsonFastBuild.selector.UtilesSelector;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ResponseMappingJsonDAO extends JsonConfDAO<ResponseMapping> {

	public ResponseMappingJsonDAO(JsonObject jo) {
		super(jo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResponseMapping getMapping() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		
		JsonObject jso=UtilesSelector.selection("_mapping::response", jo)
											.getAsJsonObject();
		ResponseMapping rm=new ResponseMapping();
		rm.setClasse(Class.forName(jso.get("class").getAsString()));
		ResponseMappingJsonDAO.aliasDecoder(jso.get("alias").getAsJsonObject());
		
		return null;
	}
	
	public static void main(String args[]) throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException {
		JsonParser jsonParser=new JsonParser();
		JsonObject jo=jsonParser.parse(new FileReader(
					new File("/home/amine/workspace/"
							+ "QuickBuildElastic/src/"
							+ "resources/model.json")))
						.getAsJsonObject();
		new ResponseMappingJsonDAO(jo).getMapping();
	
	}

}
