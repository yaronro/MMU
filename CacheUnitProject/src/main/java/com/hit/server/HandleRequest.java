package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

import java.lang.reflect.Type;

public class HandleRequest<T> implements Runnable {

	private Socket s;
	private CacheUnitController<T> controller;
	
	public final static String  ACTION = "action";
	public final static String  DELETE = "DELETE";
	public final static String  GET = "GET";
	public final static String  UPDATE = "UPDATE";

	
	public HandleRequest(Socket s,CacheUnitController<T> controller)
	{
		this.s = s;
		this.controller = controller;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Type ref; 
		T req = null;
		DataModel<T>[] dataModels;
		ObjectOutputStream output;
		ObjectInputStream input;
		try {
			 output=new ObjectOutputStream(s.getOutputStream());
			 input=new ObjectInputStream(s.getInputStream());
			
			req = (T) input.readObject();
			//parsing the json
			ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			// set the requset Obj
			Request<DataModel<T>[]> request = new Gson().fromJson((String) req, ref);
			
			switch(request.getHeaders().get(ACTION))
			{
			case DELETE: 
				controller.delete(request.getBody());
				break;
			case UPDATE:
				controller.update(request.getBody());
				break;
			case GET: 
				dataModels = controller.get(request.getBody());
				String sendToClient = "";
				for (int i = 0; i < dataModels.length; i++) {
					sendToClient += "Data [ id = "+dataModels[i].getDataModelId()+", content = "
				+dataModels[i].getDataModelContent() +" ]\n";
					
				}
				output.writeUTF(sendToClient);
				output.flush();
				break;
				
				default:System.out.println("The action is not valid"); 
				
				input.close();
				output.close();
				s.close();
			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}