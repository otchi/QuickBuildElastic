package com.edifixio.amine.jsonConfigDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.edifixio.amine.config.QueryMapping;
import com.edifixio.amine.controller.Couple;
import com.edifixio.jsonFastBuild.selector.UtilesSelector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class QueryMappingJsonDAO extends MappingJsonDAO<List<String>> {

	public QueryMappingJsonDAO(JsonObject jo) {
		super(UtilesSelector.selection("_mapping::request", jo).getAsJsonObject());
		// TODO Auto-generated constructor stub
	}

	@Override
	public QueryMapping getMapping() throws ClassNotFoundException {
		Set<Entry<String, JsonElement>> mapping = jo.get("mapping").getAsJsonObject().entrySet();
		// System.out.println();
		Iterator<Entry<String, JsonElement>> mappingIter = mapping.iterator();
		QueryMapping qm = new QueryMapping();
		qm.setAlias(super.getMapping().getAlias());
		qm.setMapClass(super.getMapping().getMapClass());
		while (mappingIter.hasNext()) {
			Entry<String, JsonElement> element = mappingIter.next();
			List<String> list=new ArrayList<String>();
			JsonArray jsonArray=element.getValue().getAsJsonArray();
			
			for(int i=0;i<jsonArray.size();i++){
				list.add(jsonArray.get(i).getAsString());
			}
			
			qm.getConfig().add((new Couple<String, List<String>>(element.getKey(), list)));
		
		}
		System.out.println("!!!!++++"+qm.getConfig()+"+++"+qm.getAlias()+"++!!!!+\n"+qm.getMapClass());
		return qm;

		// TODO Auto-generated method stub

	}
	
	public static void main(String args[]) throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException{
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = jsonParser
				.parse(new FileReader(
						new File("/home/amine/workspace/" + "QuickBuildElastic/src/" + "resources/model.json")))
				.getAsJsonObject();
		new QueryMappingJsonDAO(jo).getMapping();
	}

}
