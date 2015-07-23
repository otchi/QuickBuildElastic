package com.edifixio.amine.jsonConfigDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.edifixio.amine.config.ResponseMapping;
import com.edifixio.amine.controller.Couple;
import com.edifixio.jsonFastBuild.selector.UtilesSelector;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ResponseMappingJsonDAO extends MappingJsonDAO<String>{

	public ResponseMappingJsonDAO(JsonObject jo) {
		super(UtilesSelector.selection("_mapping::response", jo).getAsJsonObject());
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public ResponseMapping getMapping() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Set<Entry<String, JsonElement>> mapping = jo.get("mapping")
												.getAsJsonObject().entrySet();
		//System.out.println();
		Iterator<Entry<String, JsonElement>> mappingIter = mapping.iterator();
		ResponseMapping rm=new ResponseMapping();	
		rm.setAlias(super.getMapping().getAlias());
		rm.setMapClass(super.getMapping().getMapClass());
		while (mappingIter.hasNext()) {
			Entry<String, JsonElement> element = mappingIter.next();
			rm.getConfig().add(new Couple<String, String>(element.getKey(), element.getValue().getAsString()));
		}

		 System.out.println(rm.getConfig()+"----"+rm.getAlias());

		return rm;
	}

	public static void main(String args[])
			throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException {
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = jsonParser
				.parse(new FileReader(
						new File("/home/amine/workspace/" + "QuickBuildElastic/src/" + "resources/model.json")))
				.getAsJsonObject();
		new ResponseMappingJsonDAO(jo).getMapping();

	}

}
