package com.ly.zookeeper.curator.leader;

public class ClientUser {

	private Integer clientId;
	private String clientName;
	public ClientUser() {
		super();
	}
	public ClientUser(Integer clientId, String clientName) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@Override
	public String toString() {
		return "ClientUser [clientId=" + clientId + ", clientName=" + clientName + "]";
	}
	
}
