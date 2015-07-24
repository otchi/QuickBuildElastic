package com.edifixio.amine.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edifixio.amine.controller.Couple;

public class  Mapping<Type> extends Config<Type> {

	
	private Class<?> mapClass;
	private Map<String,String> alias;
	


	public Map<String, String> getAlias() {
		return alias;
	}
	public void setAlias(Map<String, String> alias) {
		this.alias = alias;
	}

	public Class<?> getMapClass() {
		return mapClass;
	}
	public void setMapClass(Class<?> mapClass) {
		this.mapClass = mapClass;
	}
	
	public List<Couple<String,Type>> getMapping(){
		return null;
	}
	
	protected String searchInResolvedAlis(String key){
		String[] split=key.split("::");
		key="";
		String[] arrayResult=new String[split.length];
		StringBuilder result=new StringBuilder();
		for(int i=0;i<split.length;i++){
			String s=split[i];
			arrayResult[i]=(s.subSequence(0, 2).equals("$$"))?
				alias.get(s.substring(2))
				:split[i];
	
		}

		for(String r:arrayResult){
			
				result.append("::");
				result.append(r);
		}
				key=result.toString().substring(2);;
		return key;
	}
	
	
	
	
	
	public static void main(String args[]){
		Mapping<String> m=new Mapping<String>();
		m.alias=new HashMap<String, String>();
		m.alias.put("amine", "ouchiha::gaci::riki");
		System.out.println(m.searchInResolvedAlis("$$amine::test"));
	}



}
