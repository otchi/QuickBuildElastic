package com.edifixio.amine.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import com.edifixio.amine.jsonConfigDAO.QueryMappingJsonDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QueryMapping extends MappingClassAlias{

	@Override
	public Properties getMapping() {
		// TODO Auto-generated method stub
		Properties corresp=new Properties();
		List<String> queries=new LinkedList<String>();
		Iterator<Entry<Object, Object>> confIter=config.entrySet().iterator();
		
		while (confIter.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) confIter.next();
			@SuppressWarnings("unchecked")
			List<String> values= (List<String>) entry.getValue();
			for(int j=0;j<values.size();j++){
				queries.add(searchInResolvedAlis(values.get(j)));
			}
			corresp.put(entry.getKey(), queries);
			
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
			System.out.println(new QueryMappingJsonDAO(jo).getMapping().getConfig().get(0).toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		
	}
}
