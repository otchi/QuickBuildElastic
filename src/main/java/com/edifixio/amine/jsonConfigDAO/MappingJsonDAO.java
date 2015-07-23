package com.edifixio.amine.jsonConfigDAO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.edifixio.amine.config.Mapping;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class MappingJsonDAO<Type> extends ConfigJsonDAO<Mapping<Type>> {

	public MappingJsonDAO(JsonObject jo) {
		super(jo);
		// TODO Auto-generated constructor stub
	}
	

	public Mapping<Type> getMapping() throws ClassNotFoundException{	
		Mapping<Type> mapping=new Mapping<Type>();
		mapping.setClasse(Class.forName(jo.get("class").getAsString()));
		mapping.setAlias(aliasDecoder(jo.get("alias").getAsJsonObject()));
		return mapping;
	}

	protected static Map<String, String> aliasDecoder(JsonObject aliasObject) {
		Set<Entry<String, JsonElement>> set = aliasObject.entrySet();
		Iterator<Entry<String, JsonElement>> iter = set.iterator();

		while (iter.hasNext()) {
			Entry<String, JsonElement> element = iter.next();
			String proprety = element.getValue().getAsString();
			
			proprety = recurciveAliasDecoder(proprety.split("::"), aliasObject);
			System.out.println(element.getKey() + ":" + proprety);
			aliasObject.add(element.getKey(), new JsonParser().parse("\"" + proprety + "\""));
		}
		
		Map<String, String> alias=new HashMap<String, String>();
		iter=set.iterator();
		while(iter.hasNext()){
			Entry<String, JsonElement> element = iter.next();
			alias.put(element.getKey(), element.getValue().getAsString());
		}
		return alias;
	}

	private static String recurciveAliasDecoder(String[] values, JsonObject aliasObject) {
		
		StringBuffer stb = new StringBuffer();
		
		for (int i = 0; i < values.length; i++) {
			
			if (values[i].subSequence(0, 2).equals("$$")) {
				String aliasName = values[i].substring(2);
				String proprety = aliasObject.get(aliasName).toString();
				
				values[i] = recurciveAliasDecoder(proprety.split("::"), aliasObject);
				values[i] = values[i].substring(1, values[i].length() - 1);	
			}
			
			stb.append(((i != 0) ? "::" : "") + values[i]);

		}
		System.out.println(stb.toString());
		return stb.toString();
	}

}
