package com.edifixio.amine.config;

import java.util.List;

@SuppressWarnings("serial")
public class ElasticSetting extends Config<List<String>>{
	private String host;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
