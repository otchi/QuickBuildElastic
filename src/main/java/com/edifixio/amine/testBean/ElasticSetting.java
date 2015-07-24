package com.edifixio.amine.testBean;

import java.util.LinkedList;
import java.util.List;

import com.edifixio.amine.config.Config;

public class ElasticSetting extends Config<List<String>>{
	
	private String host;
	private List<String> facets=new LinkedList<String>();

	public List<String> getFacets() {
		return facets;
	}

	public void setFacets(List<String> facets) {
		this.facets = facets;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
