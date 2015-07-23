package com.edifixio.amine.jsonConfigDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.edifixio.amine.config.ElasticSetting;
import com.edifixio.jsonFastBuild.selector.UtilesSelector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ElasticSettingJsonDAO extends ConfigJsonDAO<ElasticSetting>{

	public ElasticSettingJsonDAO(JsonObject jo) {
		super(jo.get("_mapping").getAsJsonObject());
		// TODO Auto-generated constructor stub
	}

	@Override
	public ElasticSetting getMapping() throws ClassNotFoundException{
		// TODO Auto-generated method stub
	
		
		Set<Entry<String, JsonElement>> jsonSet=jo.get("index")
													.getAsJsonObject()
													.entrySet();
		Iterator<Entry<String, JsonElement>> indexSetIter =jsonSet.iterator();
		HashMap<String,List<String>> indexes=new HashMap<String, List<String>>();
		List<String> listeOfType;
		while(indexSetIter.hasNext()){
			Entry<String, JsonElement> index=indexSetIter.next();
			listeOfType=new ArrayList<String>();
			JsonArray jsa=index.getValue().getAsJsonArray();
			for(int i=0;i<jsa.size();i++){
				listeOfType.add(jsa.get(i).getAsString());
			}
			indexes.put(index.getKey(), listeOfType);
			
		}
		ElasticSetting elasticSetting = new ElasticSetting();
		elasticSetting.setHost(jo.get("host").getAsString());
		elasticSetting.putAll(indexes);
		System.out.println(elasticSetting+"----"+elasticSetting.getHost());
		
		return elasticSetting;
	}
/*******************************************test*****************************************/

	public static void main (String args[]) throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException{
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = jsonParser
				.parse(new FileReader(
						new File("/home/amine/workspace/" + "QuickBuildElastic/src/" + "resources/model.json")))
				.getAsJsonObject();
		new ElasticSettingJsonDAO(jo).getMapping();

	}
}
