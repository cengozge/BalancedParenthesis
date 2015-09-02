package com.aol.challenge.Multithread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aol.challenge.Rules.RulesService;

public class MultiThreadImpl implements Runnable {

	private Thread t;
	private String threadName;
	
	private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	RulesService service = (RulesService) context.getBean("rulesService");

	MultiThreadImpl( String name){
		threadName = name;
		System.out.println("Created " +  threadName );
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
		System.out.println("Running " +  threadName );
		boolean isValid = false;
		boolean result = false;
		String s = "{[]()()]}";
		try {
			isValid = service.isValidCharacters(s);
			if(isValid){
				result = service.startRule(s.toCharArray());
				System.out.println("result is for " + threadName +result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
