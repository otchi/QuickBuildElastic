package com.edifixio.amine.jsonConfigDAO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class JsonConfDAO<Type extends HashMap<String, ?>> {

	protected JsonObject jo;

	public JsonConfDAO(JsonObject jo) {
		
		this.jo = jo;
	}

	public abstract Type getMapping() throws ClassNotFoundException;

	protected static void aliasDecoder(JsonObject aliasObject) {
		Set<Entry<String, JsonElement>> set = aliasObject.entrySet();
		Iterator<Entry<String, JsonElement>> iter = set.iterator();

		while (iter.hasNext()) {
			Entry<String, JsonElement> element = iter.next();
			String proprety = element.getValue().getAsString();
			
			proprety = recurciveAliasDecoder(proprety.split("::"), aliasObject);
			System.out.println(element.getKey() + ":" + proprety);
			aliasObject.add(element.getKey(), new JsonParser().parse("\"" + proprety + "\""));
		}
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
