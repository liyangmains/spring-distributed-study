package com.ly.mongodb.entity;

public class test {
	private String site;
	private String name;
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "test [site=" + site + ", name=" + name + "]";
	}
	
}
