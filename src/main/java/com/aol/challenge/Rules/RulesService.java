package com.aol.challenge.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service("rulesService")
public class RulesService {

	private static final Logger LOGGER = Logger.getLogger(RulesService.class.getName());
	
	private static final String OPEN_CURLY = "{";
	private static final String OPEN_BRACKET = "[";
	private static final String OPEN_PARANTHESIS = "(";
	private static final String CLOSE_CURLY = "}";
	private static final String CLOSE_BRACKET = "]";
	private static final String CLOSE_PARANTHESIS = ")";
	
	public int opensCounter = 0;
	public int closeCounter = 0;
	
	List<String> list = new ArrayList<String>();
	Stack<String> stack = new Stack<String>();
	
	public boolean startRule(char[] charArray){
		
		for (int i = 0; i < charArray.length; i++) {
            list.add(String.valueOf(charArray[i]));
        }
		
		for (int i = 0; i < list.size(); i++) {
			String siradaki = list.get(i);
			if(isOpen(list.get(i))){
				if(!stack.isEmpty()){
					String pop = stack.pop();//may use peek() but changes behind if rule
					LOGGER.log(Level.INFO, "Element is popped" + pop +" and send the rule implementation with " + siradaki);
					if(rule(pop, siradaki)){
						LOGGER.log(Level.INFO, "True for the rule, push them into stack, " + pop + " and " + siradaki);
						stack.push(pop);
						stack.push(siradaki);
					}
					else{
						LOGGER.log(Level.INFO, "Invalid open one, "+ siradaki + " cannot be after " + pop);
						//System.out.println("Invalid open one, " + siradaki + " cannot be after " + pop);
						return false;
					}
				}
				else if(i == list.size()-1){
					LOGGER.log(Level.INFO, "#ofOpen is not equal to #ofClosed");
					//System.out.println("#ofOpen is not equal to #ofClosed ");
				}
				else{
					stack.push(siradaki);
					LOGGER.log(Level.INFO,"Element is pushed to stack, " + siradaki);
				}
			}
			else if(isClosed(siradaki)){
				if(stack.isEmpty()){
					LOGGER.log(Level.INFO,"This is a closed one but one " + siradaki +" but before is not its pair of open");
					//System.out.println("This is a closed one but one before is not its pair of open ");
					return false;
				}
				else{
					String pop2 = stack.pop();
					if(pairOf(pop2, siradaki)){
						if(stack.isEmpty() && i == list.size()-1){
							LOGGER.log(Level.INFO,"(True) Excellent expression!");
							return true;
							//System.out.println("(True) Excellent expression!");
						}
					}
					else{
						LOGGER.log(Level.INFO,"Unexpected closing one, " + pop2 + " and " + siradaki);
						//System.out.println("Unexpected closing one, " + pop2 + " ve " + siradaki);
						return false;
					}
				}
			}
		}
		return false;

	}

	private boolean rule(String first, String second){
		return ( OPEN_PARANTHESIS.equals(first) && OPEN_CURLY.equals(second) ) ||
				( OPEN_CURLY.equals(first) && OPEN_BRACKET.equals(second) ) ||
				( OPEN_BRACKET.equals(first) && ( OPEN_PARANTHESIS.equals(second) || OPEN_CURLY.equals(second) || OPEN_BRACKET.equals(second) ) );
	}
	
	private boolean isOpen(String s){
		return OPEN_CURLY.equals(s) || OPEN_BRACKET.equals(s) || OPEN_PARANTHESIS.equals(s);
	}
	
	private boolean isClosed(String s){
		return CLOSE_CURLY.equals(s) || CLOSE_BRACKET.equals(s) || CLOSE_PARANTHESIS.equals(s);
	}
	
	private boolean pairOf(String first, String second){
		return (OPEN_CURLY.equals(first) && CLOSE_CURLY.equals(second)) || 
				(OPEN_BRACKET.equals(first) && CLOSE_BRACKET.equals(second)) || 
				(OPEN_PARANTHESIS.equals(first) && CLOSE_PARANTHESIS.equals(second));
	}
	
	private int countOpens(List<String> list){
		for (String string : list) {
			if(isOpen(string)){
				opensCounter++;
			}
		}
		setOpensCounter(opensCounter);
		return opensCounter;
	}
	
	private int countCloses(List<String> list){
		for (String string : list) {
			if(isOpen(string)){
				closeCounter++;
			}
		}
		setCloseCounter(closeCounter);
		return closeCounter;
	}
	
	public boolean isValidCharacters(String dizi) throws Exception{
		Pattern p = Pattern.compile("\\{|\\[|\\(|\\)|\\]|\\}");
		Matcher m2 = p.matcher(dizi);
		
		if(!m2.find()){
			System.out.println("Invalid characters..");
			throw new Exception("Invalid characters..");
		}
		else{
			return true;
		}
	}

	public int getOpensCounter() {
		return opensCounter;
	}

	public void setOpensCounter(int opensCounter) {
		this.opensCounter = opensCounter;
	}

	public int getCloseCounter() {
		return closeCounter;
	}

	public void setCloseCounter(int closeCounter) {
		this.closeCounter = closeCounter;
	}
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}

}
