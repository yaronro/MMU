package com.hit.dm;

import java.io.IOException;	
import java.io.Serializable;

@SuppressWarnings("serial")
public class DataModel<T> extends Object implements Serializable {
	
	private Long id;
	private T content;
	
	public DataModel (Long id, T content ) throws IOException
	{
		this.id = id;
		this.content = content;
		
		
		
	}
	
	public void setDataModelId(Long id)
	{
		this.id = id;
	}
	
	public void setDataModelContent(T content)
	{
		this.content = content;
	}
	
	
	public long getDataModelId() {
		return id;
	}
	
	public T getDataModelContent()
	{
		return content;
	}
}
