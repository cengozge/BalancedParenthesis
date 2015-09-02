package com.aol.challenge.Main;


import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aol.challenge.Rules.RulesService;

public class Main {

	private static Scanner scanner;
	private static ApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		RulesService service = (RulesService) context.getBean("rulesService");

		scanner = new Scanner(System.in);
		String dizi = scanner.next();
		try {
			if(service.isValidCharacters(dizi)){
				char[] charArray = dizi.toCharArray();
				service.startRule(charArray);
			}

		} 
		catch (Exception e) {
		}

	}

}
