package com.test.bus_server;

public enum MessageTypeEnum {
	
	B002("B002", "会话连接"), 
	A011("A011", "黑名单下载"), 
	A016("A016", "白名单下载"), 
	A042("A042", "公交脱机交易记录上传");
	
	//报文类型
	private String type;
	
	//报文说明
	private String desc;
	
	private MessageTypeEnum(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return type;
	}

}
