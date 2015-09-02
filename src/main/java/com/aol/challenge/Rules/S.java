package com.aol.challenge.Rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class S {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Stack<String> stack = new Stack<String>();

        List<String> list = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String dizi = scanner.next();
        Pattern p = Pattern.compile(":\\}:\\]:\\):\\{:\\[:\\(:");
        Matcher m2 = p.matcher(dizi);
        if(!m2.find()){
            System.out.println("Invalid characters..");
            return;
        }
        char[] charArray = dizi.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            list.add(String.valueOf(charArray[i]));
        }

        for (int i = 0; i < list.size(); i++) {
            String siradaki = list.get(i);
            if(isOpen(list.get(i))){
                if(!stack.isEmpty()){
                    String pop = stack.pop();
                    if(rule(pop, siradaki)){
                        stack.push(pop);
                        stack.push(siradaki);
                    }
                    else{
                        System.out.println("Invalid open one, "+siradaki + " cannot be after "+ pop);
                        return;
                    }
                }
                else{
                    stack.push(siradaki);
                }
            }
            else if(isClosed(siradaki)){
                if(stack.isEmpty()){
                    System.out.println("This is a closed one but one before is not its pair of open ");
                    return;
                }
                else{
                    String pop2 = stack.pop();
                    if(pairOf(pop2, siradaki)){
                        if(stack.isEmpty()){
                            System.out.println("Excellent expression!");
                        }
                    }
                    else{
                        System.out.println("Unexpected closing one, "+pop2 + " ve " +siradaki);
                        return;
                    }
                }
            }
        }

    }

    public static boolean rule(String first, String second){
        return ( "(".equals(first) && "{".equals(second) ) ||
                ( "{".equals(first) && "[".equals(second) ) ||
                ( "[".equals(first) && ( "(".equals(second) || "{".equals(second) || "[".equals(second) ) );
    }
    public static boolean isOpen(String s){
        return "{".equals(s) || "[".equals(s) || "(".equals(s);
    }
    public static boolean isClosed(String s){
        return "}".equals(s) || "]".equals(s) || ")".equals(s);
    }
    public static boolean pairOf(String first, String second){
        return ("{".equals(first) && "}".equals(second)) || ("[".equals(first) && "]".equals(second)) || ("(".equals(first) && ")".equals(second));
    }

}
