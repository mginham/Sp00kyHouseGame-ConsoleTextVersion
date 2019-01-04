/*
 * Project:		Sp00ky House (text version)
 * Program:		Misc
 * Author:		Megan Ginham
 * Date:		December 21, 2018
 */

package codes;

import java.util.Scanner;

public class Misc {

	// Global variables //
	public static Scanner scanner = new Scanner(System.in);
	
	// Methods //
	public static int validateInt(int min, int max) {
		// Declare and initialize variables
		int result = 0;
		boolean valid = true;
		
		do {
			valid = true;
			
			try {
				result = scanner.nextInt();
			} catch (Exception E){
				scanner.next();
				System.out.println("Sorry, that's not an option");
				valid = false;
			}
			
			if (valid && (result < min || result > max)) {
				System.out.println("Sorry, that's not an option");
				valid = false;
			}
			
		} while (!valid);
		
		return result;
	} // end validateInt
	public static char validateChar() {
		// Declare and initialize variables
		String userInput = "";
		char userChar = 0;
		boolean validInput = false;
		
		do { // Loop until input is valid
			validInput = true; // Assume input is valid initially
		
			// Prompt user for input
				userInput = scanner.next();
			
			if(userInput.length() != 1) { // If invalid input, clear buffer (remove existing input)
				System.out.print("Sorry, that's not an option");
				validInput = false;
				scanner.nextLine();
			}
			else {
				userChar = userInput.charAt(0);
			}
		} while(!validInput);
		
		return userChar;
	} // end getValidatedChar
	static int[] generateCode(int length, int max, int min) {
		int[] code = new int[length];
		int randNum = -1;
		int n = 0; // counter
		
		for(int i = 0; i < length; i++) {
			randNum = (int) (Math.random()*max-min); // generates random numbers between min and max values
			code[n++] = randNum;
		}
		
		return code;
	} // end generateCode
	static int generateCode(int max, int min) {
		int code = 0;
		
		code = (int) (Math.random()*max-min);
		
		return code;
	} // end generateCode
	static boolean enterSafeCode(int[] code, int min, int max) {
		int number = -1;
		boolean crackedCode = true;
		
		for(int i = 0; i < code.length; i++) {
			if(i == 0)
				System.out.print("You turn the dial to: ");
			else
				System.out.print("Then: ");
			
			number = validateInt(min, max);
			
			if(number != code[i]) {
				crackedCode = false;
			}
		}
		
		return crackedCode;
	} // end enterSafeCode
	static boolean enterDoorCode(int[] code) {
		boolean crackedCode = true, validInput = true;
		String input = "";
		char[] combo = new char[code.length];
		
		// Validate input
		do {
			validInput = true;
			
			System.out.println("\nEnter a combition:");
			input = scanner.next();
			
			if(input.length() != code.length) {
				System.out.println("Sorry, that's not an option");
				validInput = false;
			}
			else {
				for(int i = 0; i < code.length; i++) {
					combo[i] = input.charAt(i);
					
					if(combo[i] < 48 || combo[i] > 57) { // checking if char is a number
						System.out.println("Sorry, that's not an option");
						validInput = false;
						break;
					}
				}
			}
		} while(!validInput);
		
		// Check if combo matches code
		for(int i = 0; i < code.length; i++) {
			if(Character.getNumericValue(combo[i]) != code[i]) {
				crackedCode = false;
			}
		}
		
		return crackedCode;
	} // end enterDoorCode
	
} // end class Misc
