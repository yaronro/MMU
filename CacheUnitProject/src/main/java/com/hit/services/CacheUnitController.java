package com.hit.services;

import com.hit.dm.DataModel;

public class CacheUnitController<T>{

	private CacheUnitService unitService = new CacheUnitService<>();

	public CacheUnitController(){}
	 
	
	
	@SuppressWarnings("hiding")
	public <T> boolean update(DataModel<T>[] dataModels)
	{	

		
		return unitService.update(dataModels);
	}
	
	
	
	@SuppressWarnings("hiding")
	public <T> boolean delete(DataModel<T>[] dataModels) throws Exception
	{
		return unitService.delete(dataModels);
		
	}
	
	
	
	@SuppressWarnings("hiding")
	public <T> DataModel<T>[] get(DataModel<T>[] dataModels)
	{
		return unitService.get(dataModels);
		
	}

}