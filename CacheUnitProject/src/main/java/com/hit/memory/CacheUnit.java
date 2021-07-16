package com.hit.memory;

import com.hit.algorithm.IAlgoCache;	
import com.hit.dao.IDao;
import com.hit.dm.DataModel;


//constructor
public class CacheUnit<T> extends Object{

	private IAlgoCache<Long,DataModel<T>> algo;
	private IDao<Long,DataModel<T>> dao;

	public CacheUnit(IAlgoCache<Long,DataModel<T>> algo,
			IDao<Long,DataModel<T>> dao)
	{
		this.algo = algo;
		this.dao = dao;

	}

	@SuppressWarnings("null")
	public DataModel<T>[] getDataModels(Long[] ids) throws Exception
	{
		int indx=0;
		DataModel<T>[] list = new DataModel[ids.length];
		DataModel<T>tmpCache = new DataModel<T>(null, null);
		DataModel<T>tmpFile =  new DataModel<T>(null, null);;

		for(int i = 0; i<ids.length; i++)
		{
			if(ids[i] != null)
			{
				//find in the hash the element
				tmpCache = algo.getElement(ids[i]);
				if(tmpCache != null)
				{
					//if we found 
					list[indx++]= tmpCache;
				}
				else
				{
					//if we not found, find in the file
					tmpFile = dao.find(ids[i]);
					if(tmpFile != null)
					{
						//and put it in the hash
						algo.putElement(tmpFile.getDataModelId(),tmpFile);
						list[indx++]= tmpFile;
					}
				}
			}
		}

		return list;
	}

	@SuppressWarnings("null")
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels)
	{
		DataModel<T> model;
		DataModel<T> models[] = new DataModel[datamodels.length];
		int j = 0;
		for(int i =0; i<datamodels.length; i++)
		{
			model = datamodels[i];

			if(model != null )
			{
				algo.putElement(model.getDataModelId(),  model);
				dao.save(model);
				models[j++] = model;
			}
		}
		return models;

	}

	public void removeDataModels(Long[] ids)
	{
		for(Long id : ids)
		{
			algo.removeElemnt(id);
		}
	}

}
