package com.saugatlama.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NewOrderSingleParser {
	
	/*
	 * Function to get message from file 
	 */
	private static String[] getMessage(String fileName) {
		try {
			String content = new Scanner(new File("NewOrderSingle.txt")).next();
			
			//uses <SOH> as a delimiter and parses the content
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
		
		//Booleans to check if mandatory fields are present
		boolean beginString = false;	//Field 8
		boolean bodyLength = false;		//Field 9
		boolean msgType = false;		//Field 35
		boolean senderCompID = false;	//Field 49
		boolean targetCompID = false;	//Field 56
		
		for(int i = 0; i<tokens.length;i++){
			//splitting every field by delimiter =
			String[] temp = tokens[i].split("\\=");
			//checking for mandatory fields
			switch(Integer.valueOf(temp[0])){
				case 8:
					beginString = true;
				case 9:
					bodyLength = true;
				case 35:
					msgType = true;
				case 49:
					senderCompID = true;
				case 56:
					targetCompID = true;
			}
		}
		
		
		if(!beginString){
			System.out.println("Error: Mandatory field 8(BeginString) is missing.");
		}
		else if(!bodyLength){
			System.out.println("Error: Mandatory field 9(BodyLength) is missing.");
		}
		else if(!msgType){
			System.out.println("Error: Mandatory field 35(MsgType) is missing.");
		}
		else if(!senderCompID){
			System.out.println("Error: Mandatory field 49(SenderCompID) is missing.");
		}
		else if(!targetCompID){
			System.out.println("Error: Mandatory field 56(TargetCompID) is missing.");
		}
		else{
			System.out.println("No errors");
			for(int i =0; i<tokens.length;i++){
				System.out.println(tokens[i]);
			}
		}
		
	}
	
	/*
	 * Main function
	 */
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.print("Please input the file name.");		
		String fileName = input.nextLine();
		String[] tokens = getMessage(fileName);
		
		checkErrors(tokens);
	}
}
