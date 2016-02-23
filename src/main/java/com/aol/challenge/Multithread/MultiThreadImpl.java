package com.aol.challenge.Multithread;

public class MultiThreadImpl implements Runnable {

	private Thread t;
	private String threadName;

	MultiThreadImpl( String name){
		threadName = name;
		System.out.println("Creating " +  threadName );
	}

	public void start ()
	{
		System.out.println("Starting " +  threadName );
		if (t == null)
		{
			t = new Thread (this, threadName);
			t.run();
		}
	}
	
	public void run() {

		
	}

}
