package com.hit.dao;

import java.io.BufferedReader;		
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;


import com.hit.dm.DataModel;



public class DaoFileImpl<T> extends Object implements IDao<Long,DataModel<T>>{

	private String filePath;
	private int capacity;

	private PrintWriter out;
	private HashMap <Long,DataModel<T>> fileHash = new HashMap<>();




	public DaoFileImpl(String filePath) throws IOException{

		this.filePath = filePath;
	}

	public DaoFileImpl(String filePath, int capacity) throws IOException{
		this.filePath = filePath;
		this.capacity = capacity;

	}

	@Override
	public void delete(DataModel<T> entity) throws Exception {

		long l = entity.getDataModelId();
		String s = (String) entity.getDataModelContent();


		// insert the data model to a string.
		String line = l +", "+s;
		String currLine;

		out = new PrintWriter(new FileWriter(filePath));

		for (Entry entry : fileHash.entrySet()) 
		{
			out.println(fileHash.get(entry.getKey()).getDataModelId()+", "+ fileHash.get(entry.getKey()).getDataModelContent());

		}
		out.flush();


		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		ArrayList<String>arr = new ArrayList<>();
		//add all the file to arrayList

		while((currLine = reader.readLine())!= null)
		{
			arr.add(currLine);
		}
		reader.close();


		//find in the arrayList the line
		for(int i = 0; i<arr.size(); i++)
		{
			if(arr.get(i).equals(line)) // than remove
			{
				arr.remove(i);
			}
		}
		//remove from hash.
		fileHash.remove(entity.getDataModelId());

		//write all the ArrayList into the file 
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

		for(String str : arr)
		{
			writer.write(str);
			writer.newLine();
		}
		writer.flush();
		writer.close();
		out.close();

	}

	@SuppressWarnings({ "null", "unchecked" })
	@Override
	public DataModel<T> find (Long id) throws IOException  {

		DataModel<T> model = new DataModel(null, null);
		Long tmp = null;

		//		out = new PrintWriter(new FileWriter(filePath));
		//		out.println(fileHash);
		//		out.close();

		out = new PrintWriter(new FileWriter(filePath));

		for (Entry<Long,DataModel<T>> entry : fileHash.entrySet()) 
		{
			Long id1 = fileHash.get(entry.getKey()).getDataModelId();
			String s  = (String) fileHash.get(entry.getKey()).getDataModelContent();

			out.println(fileHash.get(entry.getKey()).getDataModelId()+", "+ (String)fileHash.get(entry.getKey()).getDataModelContent());

		}
		out.close();

		@SuppressWarnings("resource")
		Scanner s = new Scanner (new File (filePath)).useDelimiter(", |\n");
		while(s.hasNext())
		{

			if(s.hasNextLong())
			{
				tmp = Long.valueOf(s.next());
				if(tmp == id)
				{
					model.setDataModelId(id);
					model.setDataModelContent((T) s.next()); //not sure
					return model;	
				}
			}
			else
				s.next();
		}	

		return null;
	}

	@Override
	public void save(DataModel<T> entity) {

		if(!fileHash.containsKey(entity.getDataModelId()))
			fileHash.put(entity.getDataModelId(), entity);

	}
}