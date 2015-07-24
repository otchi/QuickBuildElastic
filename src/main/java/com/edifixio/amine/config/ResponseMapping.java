package com.edifixio.amine.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import com.edifixio.amine.controller.Couple;
import com.edifixio.amine.jsonConfigDAO.ResponseMappingJsonDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ResponseMapping extends Mapping<String>{
	private List<Couple<String, String>> sourceMapping=new LinkedList<Couple<String,String>>();
	
	
	public List<Couple<String, String>> getSourceMapping() {
		return sourceMapping;
	}

	public void setSourceMapping(List<Couple<String, String>>sourceMapping) {
		this.sourceMapping = sourceMapping;
	}

	@Override
	public List<Couple<String,String>> getMapping() {
		// TODO Auto-generated method stub
			List<Couple<String,String>> corresp=new LinkedList<Couple<String,String>>();
			for(int i=0;i<config.size();i++){
				Couple<String,String> conf=config.get(i);
					corresp.add(
							new Couple<String, String>(
									conf.getKey(),
									searchInResolvedAlis(conf.getValue())));
				}
			
			return corresp;
	}
	
	public static void main(String args[]){
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
			System.out.println(new ResponseMappingJsonDAO(jo).getMapping().getMapping());
			System.out.println(new ResponseMappingJsonDAO(jo).getMapping().getConfig().get(0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
