package com.hit.server;

import java.io.Serializable;
import java.util.Map;

import com.hit.dm.DataModel;

public class Request<T> implements Serializable {
	private Map<String,String> headers;
	private T body;
	
	public Request(Map<String,String> headers, T body)
	{
		this.body=body;
		this.headers = headers;
	}
	

	public Map<String,String> getHeaders()
	{
		return headers;
		
	}
	
	
	
	public void setHeaders(Map<String,String> headers)
	{
		this.headers = headers;
	}


	
	public T getBody() {
		
		return (T) body;
	}

	
	
	public void setBody(T body) {
		this.body = body;
	}


	@Override
	public String toString() {
		return "Request [body=" + body + "]";
	}

	
}