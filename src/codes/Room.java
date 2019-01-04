/*
 * Project:		Sp00ky House (console text version)
 * Program:		Room (abstract)
 * Author:		Megan Ginham
 * Date:		December 21, 2018
 */

package codes;

public abstract class Room {
	
	// Global variables //
	static boolean escaped = false, returningRoom = false, returningHall = false, codeDigitDiscovered = false;
	static int codeDigit = -1, choice = -1;
	static char response = 0;
	static boolean validInput = true;
	
	// Methods //
	abstract void printStart();
	abstract void printDesc();
	abstract void printOptions();
	
} // end class Room
