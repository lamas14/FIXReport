package com.saugatlama.Parser;

import java.io.*;
import java.util.Scanner;

public class NewOrderSingleParser {
	
	
	private static String[] getMessage(String fileName) {
		try {
			String content = new Scanner(new File("NewOrderSingle.txt")).next();
			String[] tokens = content.split("\\<SOH>");
			return tokens;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 * Function to check errors
	 * Might have to use map or dictionary
	 */
	private static void checkErrors(String[] tokens) {
		
		
	}
	
	public static void main(String[] args){
		String fileName = "NewOrderSingle.txt";
		String[] tokens = getMessage(fileName);
		for(int i =0; i<tokens.length;i++){
			System.out.println(tokens[i]);
		}
		
		checkErrors(tokens);
	}
}
