package com.hit.dao;

//import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDao <ID extends java.io.Serializable , T> {
 
	public void delete (T entity) throws Exception;
	public T find (ID id) throws IOException;
	public void save (T entity);
}