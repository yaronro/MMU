package com.hit.util;

import java.beans.PropertyChangeListener;	
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import com.hit.server.HandleRequest;
import com.hit.server.Server;

public class CLI implements Runnable{
	private PropertyChangeSupport changes = new PropertyChangeSupport(this); 
	private Scanner scanner;
	private static final String START = "starting server";
	private static final String SHUTDOWN = "shutdown server";
	private static final String ENTER_COMMAND = "please enter command";
	private static final String NOT_VALID_INPUT = "please enter valid input";
	boolean status = false;
	private InputStream in;
	private OutputStream out;

	
	public CLI(InputStream in , OutputStream out)
	{
		scanner = new Scanner(in);
		this.in=in;
		this.out=out;
	}
	

	@Override
	public void run() {
		while (true)
		{
			write(ENTER_COMMAND);
			String input = scanner.nextLine();
//			while (!input.equalsIgnoreCase("shotdown"))
//			{
				switch (input)
				{
				case "start":
				{
					write(START);
					status = true;
					
					Server server = new Server(34567);
					Thread t = new Thread(server);	
					t.start();
					
					input = scanner.nextLine();

				}
				case "stop":
				{
					write(SHUTDOWN);
					status = false;

				}
//				default:
//					write(NOT_VALID_INPUT);
				}

			}
//		}

	}


	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		changes.addPropertyChangeListener(pcl);
	}


	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		changes.removePropertyChangeListener(pcl);
	}


	public void write(String string)
	{
		System.out.println(string);
	}

}