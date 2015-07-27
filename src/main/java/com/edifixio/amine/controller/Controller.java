package com.edifixio.amine.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.edifixio.amine.config.ElasticSetting;
import com.edifixio.amine.config.QueryMapping;
import com.edifixio.amine.config.ResponseMapping;
import com.edifixio.amine.jsonConfigDAO.ElasticSettingJsonDAO;
import com.edifixio.amine.jsonConfigDAO.QueryMappingJsonDAO;
import com.edifixio.amine.jsonConfigDAO.ResponseMappingJsonDAO;
import com.edifixio.amine.testBean.MyRequest;
import com.edifixio.jsonFastBuild.selector.UtilesSelector;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.Search.Builder;

public class Controller {
	ElasticSetting elasticSetting;
	QueryMapping queryMapping;
	ResponseMapping responseMapping;
	Object queryBean;
	Map<String, Map<String, String>> facets;
	JsonObject query;
	JsonObject response;

	public Controller(ElasticSetting elasticSetting, QueryMapping queryMapping, 
						ResponseMapping responseMapping,Object queryBean, 
						Map<String, Map<String, String>> facets, JsonObject query) {
		
		this.elasticSetting = elasticSetting;
		this.queryMapping = queryMapping;
		this.responseMapping = responseMapping;
		this.queryBean = queryBean;
		this.facets = facets;
		this.query = query;
		System.out.println("---------\n" + query);
	}

	public void processingQuery() throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {

		Class<?> queryClass = queryMapping.getMapClass();
		Object queryObject = queryBean;

		Properties mapping = queryMapping.getMapping();
		Iterator<Entry<Object, Object>> mapIter = mapping.entrySet().iterator();

		while (mapIter.hasNext()) {
			Entry<Object, Object> couple = mapIter.next();
			Method m = queryClass
					.getMethod("get" + 
								((String)couple.getKey())
										.substring(0, 1)
										.toUpperCase() +
								((String)couple.getKey()).substring(1));

			String value = (String) m.invoke(queryObject);

			@SuppressWarnings("unchecked")
			List<String> requestValue = (List<String>) couple.getValue();
			Iterator<String> requestValueIter = requestValue.iterator();

			while (requestValueIter.hasNext()) {

				String request = requestValueIter.next();
				int fieldIndex = request.lastIndexOf("::");
				String path = request.substring(0, fieldIndex);
				String field = request.substring(fieldIndex + 2);
				
				JsonObject jso = UtilesSelector.selection(path, query)
												.getAsJsonObject();
				jso.remove(field);
				jso.addProperty(field, value);

			}

			System.out.println(query);
			processingResponse();
			try {
				this.ConfigElasticClient();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void ConfigElasticClient() throws IOException{
		JestClient jestClient =ElasticClient.getElasticClient(elasticSetting.getHost()).getClient();
		//elasticSetting.
		System.out.println(query);
		Builder build=new Search.Builder(query.toString());
		Properties listIndex=elasticSetting.getConfig();
		Iterator<Entry<Object, Object>> listIndexIterator=listIndex.entrySet().iterator();
		
		while (listIndexIterator.hasNext()){
			Entry<Object, Object> index=listIndexIterator.next();
			build.addIndex((String) index.getKey());
			build.addType((List<String>) index.getValue());
		}

		response=jestClient.execute(build.build()).getJsonObject();		
	}
	
	public List<Object> processingResponse() throws InstantiationException, IllegalAccessException,
													NoSuchMethodException, SecurityException, 
													IllegalArgumentException, InvocationTargetException{
		/*
		Class<?> responseClass=responseMapping.getMapClass();
		List<Object> responseObjects=new LinkedList<Object>();

		List<Couple<String, String>> mapping = responseMapping.getMapping();
		
		Iterator<Couple<String, String>> mappingIter=mapping.iterator();
		
		while(mappingIter.hasNext()){
			Couple<String, String> couple=mappingIter.next();
			Object responseobject=responseClass.newInstance();
			Method m=responseClass.getMethod("set"+
									couple.getKey().substring(0, 1)
											.toUpperCase() +
									couple.getKey().substring(1), 
									String.class);
			//System.out.println(m.invoke(responseobject, 
			//UtilesSelector.selection(couple.getValue(),this.response)
					//	.getAsJsonObject().toString()));*/
			//;
			
		//}
		
		return null;
	}

	public static void main(String args[])
			throws JsonIOException, JsonSyntaxException, FileNotFoundException, ClassNotFoundException {
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = jsonParser
				.parse(new FileReader(
						new File("/home/amine/workspace/" + "QuickBuildElastic/src/" + "resources/model.json")))
				.getAsJsonObject();
		try {
			new Controller(new ElasticSettingJsonDAO(jo).getMapping(), new QueryMappingJsonDAO(jo).getMapping(),
					new ResponseMappingJsonDAO(jo).getMapping(), new MyRequest(), null,
					jo.get("_query").getAsJsonObject())
			.processingQuery();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// new QueryMappingJsonDAO(jo).getMapping().getCorresp();

	}

}
