package com.ly.entity;

import java.io.Serializable;

public class ResponseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5946953007650136986L;
	private Object data;
	private Integer code;
	private String content;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "ResponseEntity [data=" + data + ", code=" + code + ", content=" + content + "]";
	}
	
}
