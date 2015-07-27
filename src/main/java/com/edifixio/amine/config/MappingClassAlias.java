package com.edifixio.amine.config;

import java.util.Properties;

public class  MappingClassAlias extends MappingClass {

	private Properties alias;
	
	public Properties getAlias() {
		return alias;
	}
	public void setAlias(Properties alias) {
		this.alias = alias;
	}


	
	public Properties getMapping(){
		return null;
	}
	
	protected String searchInResolvedAlis(String key){
		String[] split=key.split("::");
		key="";
		String[] arrayResult=new String[split.length];
		StringBuilder result=new StringBuilder();
		for(int i=0;i<split.length;i++){
			String s=split[i];
			arrayResult[i]=(String) ((s.subSequence(0, 2).equals("$$"))?
				alias.get(s.substring(2))
				:split[i]);
	
		}
		for(String r:arrayResult){
			
				result.append("::");
				result.append(r);
		}
				key=result.toString().substring(2);;
		return key;
	}
	
	
	
	
	
	public static void main(String args[]){
		MappingClassAlias m=new MappingClassAlias();
		m.alias=new Properties();
		m.alias.put("amine", "ouchiha::gaci::riki");
		System.out.println(m.searchInResolvedAlis("$$amine::test"));
	}



}
