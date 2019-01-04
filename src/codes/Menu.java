/*
 * Project:		Sp00ky House (console text version)
 * Program:		Menu
 * Author:		Megan Ginham
 * Date:		December 21, 2018
 */

package codes;

public class Menu {
	
	// Global variables //
	static int userChoice = -1;

	// Methods //
	static int mainMenu() {
		System.out.println("//=====================SP00KY HOUSE=====================//"
				     + "\n\n    1. Play game"
					   + "\n    0. Quit");
		userChoice = Misc.validateInt(0, 1);
			
		return userChoice;
	} // end mainMenu
	static boolean endMenu(Boolean play) {
		System.out.println("\n//======================================================//"
					   + "\n\n    1. Play again"
						 + "\n    0. Quit");
		userChoice = Misc.validateInt(0, 1);
		
		if(userChoice == 0) // quit game
			play = false;
		else if(userChoice == 1) // play again
			play = true;
		
		return play;
	} // end endMenu
	
} // end class Menu
