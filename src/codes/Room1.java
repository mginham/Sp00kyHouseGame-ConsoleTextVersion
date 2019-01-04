/*
 * Project:		Sp00ky House (console text version)
 * Program:		Room1 (bedroom)
 * Author:		Christopher Mohan, Megan Ginham
 * Date:		December 21, 2018
 * 
 * How to find codeDigit:
 *   1. inspect bed, look under mattress
 *   2. inspect nightstand, open top drawer to reveal codeDigit
 */

package codes;

public class Room1 extends Room {
	
	// Global variables //
	public static int[] safeCode = new int[4];
	public static boolean doorUnlocked, drawerUnlocked, safeDiscovered, safeUnlocked;
	public static boolean hasSilverKey, hasRedKey; // silver -> nightstand; red -> door
	
	// Constructor //
	public Room1(int[] frontDoorCode) {
		reset(frontDoorCode);
	} // end constructor
	
	// Methods //
	static void reset(int[] frontDoorCode) {
		codeDigit = frontDoorCode[0];
		codeDigitDiscovered = false;
		
		safeCode = Misc.generateCode(3, 50, 0);
		
		escaped = false;
		returningRoom = false;
		returningHall = false;
		
		doorUnlocked = false;
		drawerUnlocked = false;
		safeDiscovered = false;
		safeUnlocked = false;
		
		hasSilverKey = false;
		hasRedKey = false;
	} // end reset
	@Override
	void printStart() {
		System.out.println("\nYou wake up on the floor in a room. You're not sure how you got here and you feel uneasy in this strange place.");
	} // end printStart
	@Override
	void printDesc() {
		System.out.println("\nIt looks like you're in someone's bedroom."
				 + "\nA dim fluorescent lightbulb flickers above you and there are oddly no windows."
				 + "\nTo your right, you see a twin sized bed with a nightstand beside it. To the left, a door.");
	} // end printDesc
	@Override
	void printOptions() {
		System.out.println("\nWhat would you like to do?"
				 		 + "\n 1. Inspect the bed"
				 		 + "\n 2. Inspect the nightstand"
				 		 + "\n 3. Inspect the door"
				 		 + "\n 4. Inspect the rug.");
	} // end printOptions
	void printBedOptions() {
		System.out.println("\nWhat would you like to do at the bed?"
						 + "\n 1. Look under the sheets"
						 + "\n 2. Look under the pillow"
						 + "\n 3. Look under the mattress"
						 + "\n 0. I'm done here");
	} // end printBedOptions
	void printNightstandOptions() {
		System.out.println("\nWhat would you like to do at the nightstand?"
						 + "\n 1. Open the top drawer"
						 + "\n 2. Open the bottom drawer"
						 + "\n 0. I'm done here");
	} // end printNightstandOptions
	void printDoorOptions() {
		System.out.println("\nWhat would you like to do at the door?"
						 + "\n 1. Turn doorknob"
						 + "\n 2. Peep through keyhole"
						 + "\n 3. Knock"
						 + "\n 0. I'm done here");
	} // end printDoorOptions
	void printRugOptions() {
		System.out.println("\nWhat would you like to do at the rug?"
					     + "\n 1. Look under the sheets"
					     + "\n 2. Look under the pillow"
					     + "\n 3. Look under the mattress"
					     + "\n 0. I'm done here");
	} // end printRugOptions
	void inspectBed() {
		returningRoom = false;
		
		do {
			printBedOptions();
			choice = Misc.validateInt(0, 3);
				
			switch(choice) {
			case 1: // Look under the sheets
				System.out.println("\nYou take a look under the sheets of the bed and find nothing.");
				break;
			case 2: // Look under the pillow
				System.out.println("You lift up the pillow and find a squished roach. Ew.");
				break;
			case 3: // Look under the mattress
				if(hasSilverKey) {
					System.out.println("Were you expecting to find something else?");
				}
				else {
					System.out.println("The mattress weighs a ton, and you struggle to even lift the corner."
								   + "\nBut something silver catches your eye, and you quickly snatch it and jump back before the mattress falls."
								   + "\n(Obtained the Silver Key)");
					hasSilverKey = true;
				}
				break;
			case 0: // Return to room
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectBed
	void inspectNightStand() {
		returningRoom = false;
		
		do {
			printNightstandOptions();
			choice = Misc.validateInt(0, 2);
			
			switch(choice) {
			case 1: // Open the top drawer
				System.out.println("\nYou slide open the top drawer. It's empty, but the number '" + codeDigit + "' is carved into the wood.");
				codeDigitDiscovered = true;
				break;
			case 2: // Open the bottom drawer
				if(hasSilverKey) {
					if(drawerUnlocked) {
						System.out.println("\nThe numbers '" + safeCode[0] + " " + safeCode[1] +  " " + safeCode[2] + "' remain carved into the wood.");
					}
					else {
						System.out.println("\nThe key you found under the mattress fits!"
										 + "\nYou open the drawer to find that it's empty. The numbers '" + safeCode[0] + " " + safeCode[1] +  " " + safeCode[2] + "' are carved into the wood.");
						drawerUnlocked = true;
					}
				}
				else {
					System.out.println("It's locked and won't budge.");
				}
				break;
			case 0: // Return to room
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectNightStand
	void inspectDoor() {
		returningRoom = false;
		
		do {
			printDoorOptions();
			choice = Misc.validateInt(0, 3);
			
			switch(choice) {
			case 1: // Turn doorknob
				if(doorUnlocked) {
					returningRoom = true;
					returningHall = true;
				}
				else {
					if(hasRedKey) {
						System.out.println("\nYou use the Red Key to unlock the door."
										 + "\nYou have escaped!");
						
						escaped = true;
						doorUnlocked = true;
						returningRoom = true;
						returningHall = true;
					}
					else if(hasSilverKey) {
						System.out.println("\nYou try to use the Silver Key to open the door."
										 + "\nIt doesn't seem to fit...");
					}
					else {
						System.out.println("\nThe doorknob won't turn. It appears to be locked."
										 + "\nMaybe there's a key somewhere in the room.");
					}
				}
				break;
			case 2: // Peep through keyhole
				System.out.println("\nYou kneel down and peer through the keyhole, but see nothing.");
				break;
			case 3: // Knock
				System.out.println("\nYou rap your knuckles against the peeling wood."
								 + "\nNo one answers.");
				break;
			case 0: // Return to room
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectDoor
	void inspectRug() {
		returningRoom = false;
		
		if(safeUnlocked) {
			System.out.println("\nThe safe is empty.");
		}
		else {
			if(!safeDiscovered) {
				System.out.println("You lift the rug and find a safe built into the floorboards.");
				
				safeDiscovered = true;
			}
			
			System.out.println("\nThe safe is locked. Would you like to enter a code? (y/n)");
			do {
				response = Misc.validateChar();
				
				validInput = true;
				
				switch(response) {
				case 'y': // enter a code
					enterSafeCode();
					
					if(safeUnlocked) {
						System.out.println("\nThe code worked!"
										 + "\nYou open the safe and find another key."
										 + "\n(Obtained the Red Key)");
						
						hasRedKey = true;
					}
					else {
						System.out.println("\nIt looks like that combination didn't work."
										 + "\nPerhaps you're missing a clue...");
					}
					break;
				case 'n': // don't enter a code
					returningRoom = true;
					break;
				default: // invalid response
					System.out.println("\nSorry, that's not an option.");
					validInput = false;
					break;
				}
			} while (!validInput);
		}
	} // end inspectRug
	void enterSafeCode() {
		System.out.println("The dial is inscribed with numbers from 0 to 50.");
		safeUnlocked = Misc.enterSafeCode(safeCode, 0, 50);
	} // end enterSafeCode
	
} // end class Room1
