package com.test.bus_server;

public class LoginResponse {
	
	private byte[] dateTime;
	
	private byte[] black_version;
	
	private byte[] connectCode;

	public byte[] getDateTime() {
		return dateTime;
	}

	public void setDateTime(byte[] dateTime) {
		this.dateTime = dateTime;
	}

	public byte[] getBlack_version() {
		return black_version;
	}

	public void setBlack_version(byte[] black_version) {
		this.black_version = black_version;
	}

	public byte[] getConnectCode() {
		return connectCode;
	}

	public void setConnectCode(byte[] connectCode) {
		this.connectCode = connectCode;
	}
	
}
