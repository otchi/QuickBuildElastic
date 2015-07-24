package com.edifixio.amine.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import com.edifixio.amine.controller.Couple;
import com.edifixio.amine.jsonConfigDAO.QueryMappingJsonDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QueryMapping extends Mapping<List<String>>{

	@Override
	public List<Couple<String,List<String>>> getMapping() {
		// TODO Auto-generated method stub
		List<Couple<String,List<String>>> corresp=new LinkedList<Couple<String,List<String>>>();
		List<String> queries=new LinkedList<String>();
		for(int i=0;i<config.size();i++){
			Couple<String, List<String>> conf=config.get(i);
			List<String> values=conf.getValue();
			for(int j=0;j<values.size();j++){
				queries.add(searchInResolvedAlis(values.get(j)));
			}
			corresp.add(new Couple<String, List<String>>(conf.getKey(), queries));
		}
		return corresp;
	}
	
	public static void main(String args[]) {
		JsonParser jsonParser = new JsonParser();
		JsonObject jo;
		try {
			jo = jsonParser
					.parse(new FileReader(
							new File("/home/amine/workspace/" 
										+ "QuickBuildElastic/src/" 
										+ "resources/model.json")
							))
					.getAsJsonObject();
			System.out.println(new QueryMappingJsonDAO(jo).getMapping().getMapping());
			System.out.println(new QueryMappingJsonDAO(jo).getMapping().getConfig().get(0).getValue().get(0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	}
}
