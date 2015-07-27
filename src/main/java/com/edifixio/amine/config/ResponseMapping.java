package com.edifixio.amine.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import com.edifixio.amine.jsonConfigDAO.ResponseMappingJsonDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ResponseMapping extends MappingClassAlias{
	private Properties sourceMapping=new Properties();
	
	
	public Properties getSourceMapping() {
		return sourceMapping;
		
	}

	public void setSourceMapping(Properties sourceMapping) {
		this.sourceMapping = sourceMapping;
	}

	@Override
	public Properties getMapping() {
		// TODO Auto-generated method stub
		Properties corresp=new Properties();
		Iterator<Entry<Object, Object>> configIter=config.entrySet().iterator();
		
		while (configIter.hasNext()) {
			Entry<Object, Object> entry =  configIter.next();
			corresp.put(entry.getKey(), searchInResolvedAlis((String)entry.getValue()));
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
