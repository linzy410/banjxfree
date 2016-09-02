package com.ericlin.mock.util;

/**
 * Created by ruweixia on 16/3/23.
 */
public class AccessToken {

	private String access_token;
	private long expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}

}
