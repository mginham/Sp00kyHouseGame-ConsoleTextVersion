/*
 * Project:		Sp00ky House (console text version)
 * Program:		Room4 (kitchen)
 * Author:		Elliot Lam, Megan Ginham
 * Date:		December 21, 2018
 * 
 * How to find codeDigit:
 *   1. inspect oven to discover mask
 *   2. inspect fridge to discover cupboard key
 *   3. inspect cupboard to reveal the hidden number
 */

package codes;

public class Room4 extends Room {

	// Global variables //
	static boolean hasCupboardKey, hasMask;
	static boolean searchedCupboard;
	
	// Constructor //
	public Room4(int[] frontDoorCode) {
		reset(frontDoorCode);
	} // end constructor
	
	// Methods //
	static void reset(int[] frontDoorCode) {
		codeDigit = frontDoorCode[3];
		codeDigitDiscovered = false;
		
		escaped = false;
		returningRoom = false;
		returningHall = false;
		
		hasCupboardKey = false;
		hasMask = false;
		searchedCupboard = false;
	} // end reset
	@Override
	void printStart() {
		System.out.println("\nThere is ruffling coming from the hanging cupboards."
						 + "\nThe fridge is also slightly ajar and the oven light is on.");
	} // end printStart
	@Override
	void printDesc() {
		System.out.println("\nYou have entered the kitchen.");
	} // end printDesc
	@Override
	void printOptions() {
		System.out.println("\nWhat would you like to do?"
						 + "\n 1. Inspect the cupboards"
						 + "\n 2. Inspect the fridge"
						 + "\n 3. Inspect the oven"
						 + "\n 0. Return to the hallway");
	} // end printOptions
	void inspectCupboard() {
		if(hasCupboardKey) {
			if(searchedCupboard) {
				System.out.println("\nThe mice have already scattered. The chewed up box of $" + codeDigit + " crackers still sits there.");
			}
			else {
				System.out.println("\nYou leap back in surprise when you find mice devouring a pack of crackers."
								 + "\nThe mice immediately scatter and you notice that the box of crackers has a large $" + codeDigit + " price tag on it."
								 + "\nThis number may be helpful.");
				
				searchedCupboard = true;
				codeDigitDiscovered = true;
			}
		}
		else {
			System.out.println("\nYou open every cupboard and find nothing but empty shelves, except for the last."
							 + "\nIt won't budge and appears to be locked."
							 + "\nIf only you had a key.");
		}
	} // end inspectCupboard
	void inspectFridge() {
		if(hasMask) {
			if(hasCupboardKey) {
				System.out.println("\nYou slip on the mask once more and search through the fridge again."
								 + "\nThere is nothing but rotten food.");
			}
			else {
				System.out.println("\nBefore you open up the fridge, you put on the mask. The disgusting scent is now somewhat bearable."
								 + "\nYou rummage through the fridge and find a disgusting amount of rotten food."
								 + "\nIn the back corner of the top shelf you find a container that says 'DO NOT OPEN'... So you open it."
								 + "\nInside is a small key."
								 + "\n(Obtained Cupboard Key)");
				
				hasCupboardKey = true;
			}
		}
		else {
			System.out.println("\nAs you open the fridge a foul smell pours out."
							 + "\nYou can't even fathom looking deeper into the fridge and immediately shut it.");
		}
	} // end inspectFridge
	void inspectOven() {
		if(hasMask) {
			System.out.println("\nYou really shouldn't eat those cookies.");
		}
		else {
			System.out.println("\nSomeone has left a batch of cookies in the oven."
							 + "\nThey may have tasted good at some point, but now there is a grey fuzz growing on the cookies."
							 + "\nYou are hungry... But not that hungry."
						   + "\n\nBesides the cookies sits a mask that looks like it could block out a significant amount of smell."
							 + "\nIt could be useful."
							 + "\n(Obtained Gas Mask)");
			
			hasMask = true;
		}
	} // end inspectOven
	
} // end class Room4
