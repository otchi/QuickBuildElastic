package com.edifixio.amine.config;

import java.util.LinkedList;
import java.util.List;

public class ElasticSetting extends Mapping{
	
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
