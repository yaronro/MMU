package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventListener;


import com.hit.services.CacheUnitController;

public class Server implements PropertyChangeListener, Runnable, EventListener
{
	protected ServerSocket serverSocket;
	public int port;
	private CacheUnitController<String> controller; 
	
	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {

			try {
				serverSocket = new ServerSocket(port);
				Socket someClient = serverSocket.accept();
				
				new Thread(new HandleRequest<String>(someClient, controller)).start();	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		
		//	.evt.getPropertyName();	

	}


}