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
	private static String[] getMessage(String fileName, String delim) {
		
		try {
			String content = new Scanner(new File("NewOrderSingle.txt")).next();
			
			//uses <SOH> as a delimiter and parses the content
			String[] tokens = content.split("\\"+ delim);
			if(!content.contains(delim)){
				System.out.println("No delimiter found");
				System.exit(1);
			}
			return tokens;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Function to check errors
	 * BeginString, BodyLength, and MsgType must be in order
	 * 
	 * 
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
			
			if(Integer.parseInt(temp[0])<0 ||Integer.parseInt(temp[0])>956){
				System.out.println("Error: Invalid Tag: " + temp[0]);
				return;
			}
			
			//checking header order
			if(i==0 && Integer.parseInt(temp[0]) !=8){
				System.out.println("Error: This is not a valid message");
				System.out.println("Make sure the Tag order is correct");
				return;
			}
			if(i==1 && Integer.parseInt(temp[0]) !=9){
				System.out.println("Error: This is not a valid message");
				System.out.println("Make sure the Tag order is correct");
				return;
			}
			if(i==2 && Integer.parseInt(temp[0]) !=35){
				System.out.println("Error: This is not a valid message");
				System.out.println("Make sure the Tag order is correct");
				return;
			}
			
			//checking for mandatory fields
			switch(Integer.parseInt(temp[0])){
				case 8:
					if(temp[1].equals("FIX.4.4")){
						beginString = true;
					}
					else{
						System.out.print(temp[1]);
						System.out.println("Error: This is not a FIX 4.4 message");
						return;
					}
					break;
				case 9:
					bodyLength = true;
					break;
				case 35:
					if(temp[1].equals("D")){
						msgType = true;
					}
					else{
						System.out.print(temp[1]);
						System.out.println("Error: This is not a NewOrderSingle message");
						return;
					}
					break;
				case 49:
					senderCompID = true;
					break;
				case 56:
					targetCompID = true;
					break;
				default:
					
					break;
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
		System.out.print("Please enter the file name: ");		
		String fileName = input.nextLine();
		
		// I am not sure what kind of delimiter FIX 4.4 uses
		// So, I used <SOH> as the delimiter
		// Please change it if it is different
		String delim = "<SOH>";
		
		String[] tokens = getMessage(fileName, delim);
		
		checkErrors(tokens);
	}
}
