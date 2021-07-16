package com.hit.services;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T>{
	
	private IAlgoCache<Long,DataModel<T>> algo;
	private IDao<Long,DataModel<T>> dao;
	private CacheUnit<T> cacheUnit  = new CacheUnit<T>(algo, dao);

	public CacheUnitService(){}


	public boolean update(DataModel<T>[] dataModels)
	{
		if(dataModels!=null)
		{
			cacheUnit.putDataModels(dataModels);
			return true;
		}
		return false;

	}

	public boolean delete(DataModel<T>[] dataModels) throws Exception
	{
		if (dataModels != null)
		{
			Long ids[] = new Long[dataModels.length];
			DataModel<T> temp = null;
			for (int i=0 ; i<dataModels.length ; i++)
			{
				temp = dataModels[i];
				if(temp !=null)
					dao.delete(temp);
			}
			cacheUnit.removeDataModels(ids);	
			return true;
		}
		return false;
	}

	public DataModel<T>[] get(DataModel<T>[] dataModels)
	{
		return dataModels;

	}


}