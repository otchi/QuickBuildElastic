package com.edifixio.amine.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.edifixio.amine.config.ElasticSetting;
import com.edifixio.amine.config.QueryMapping;
import com.edifixio.amine.config.ResponseMapping;
import com.edifixio.amine.jsonConfigDAO.ElasticSettingJsonDAO;
import com.edifixio.amine.jsonConfigDAO.QueryMappingJsonDAO;
import com.edifixio.amine.jsonConfigDAO.ResponseMappingJsonDAO;
import com.edifixio.amine.testBean.MyRequest;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class Controller {
	ElasticSetting elasticSetting; 
	QueryMapping queryMapping;
	ResponseMapping responseMapping;
	Object queryBean;
	Map<String,Map<String,String>> facets;
	JsonObject query;
	
	public Controller(	ElasticSetting elasticSetting,
						QueryMapping queryMapping,
						ResponseMapping responseMapping,
						Object queryBean,
						Map<String,Map<String,String>> facets,
						JsonObject query){
		this.elasticSetting=elasticSetting;
		this.queryMapping=queryMapping;
		this.responseMapping=responseMapping;
		this.queryBean=queryBean;
		this.facets=facets;
		this.query=query;
		System.out.println("---------\n"+
						query);
	}
	
	public void mapQuery(){
		System.out.println(queryMapping.getMapClass()==queryBean.getClass());
		Map<String, String> alias=queryMapping.getAlias();
		Set<Couple<String, List<String>>> conf=queryMapping.getConfig();
		System.out.println(alias.toString()+conf.toString());
		
		//Set s;
	//	s.i
		
		
	}
	
	
	public static void main(String args[]) throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException{
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = jsonParser
				.parse(new FileReader(
						new File("/home/amine/workspace/" + "QuickBuildElastic/src/" + "resources/model.json")))
				.getAsJsonObject();
		new Controller(new ElasticSettingJsonDAO(jo).getMapping(),
						new QueryMappingJsonDAO(jo).getMapping(),
						new ResponseMappingJsonDAO(jo).getMapping(),
						new MyRequest(),
						null,
						jo.get("_query").getAsJsonObject()).mapQuery();
						
	}

}
