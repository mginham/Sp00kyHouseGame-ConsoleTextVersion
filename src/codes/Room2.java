/*
 * Project:		Sp00ky House (console text version)
 * Program:		Room2 (bathroom)
 * Author:		Zach Raposo, Megan Ginham
 * Date:		December 21, 2018
 * 
 * How to find codeDigit:
 *   1. inspect toilet to discover gear
 *   2. inspect sink to attach gear, turn counter-clockwise to release drawer and discover the hidden number alongside key
 *   3. inspect door to unlock padlock and escape
 */

package codes;

public class Room2 extends Room {
	
	// Global variables //
	static boolean hasKey, hasGear, hasDoorKey;
	static boolean knocked, gearOnHandle;
	static boolean doorUnlocked;
	
	// Constructor //
	public Room2(int[] frontDoorCode) {
		reset(frontDoorCode);
	} // end constructor
	
	// Methods //
	static void reset(int[] frontDoorCode) {
		codeDigit = frontDoorCode[1];
		codeDigitDiscovered = false;
		
		escaped = false;
		returningRoom = false;
		returningHall = false;
		
		hasKey = false;
		hasGear = false;
		hasDoorKey = false;
		
		knocked = false;
		gearOnHandle = false;
		
		doorUnlocked = false;
	} // end reset
	@Override
	void printStart() {
		System.out.println("\nAs you open the door you see a light fixture half hanging from the ceiling, flickering on and off."
					 	 + "\nA broken mirror resides above a marble sink with a single drawer."
					 	 + "\nAcross from the sink sits a toilet which surprisingly looks the best kept out of all the fixtures."
					 	 + "\nBefore you notice it, the door slams shut behind you.");	
	} // end printStart
	@Override
	void printDesc() {
		System.out.println("\nYou have entered the bathroom.");
	} // end printDesc
	@Override
	void printOptions() {
		System.out.println("\nWhat would you like to do?"
						 + "\n 1. Inspect sink"
						 + "\n 2. Inspect toilet"
						 + "\n 3. Inspect door");
	} // end printOptions
	void printSinkOptions() {
		System.out.println("\nWhat would you like to do at the sink?"
						 + "\n 1. Turn the good handle"
						 + "\n 2. Turn the bad handle"
						 + "\n 0. I'm done here");
	} // end printSinkOptions
	void printToiletOptions() {
		System.out.println("\nWhat would you like to do at the toilet?"
						 + "\n 1. Flush"
						 + "\n 2. Grab the gear"
						 + "\n 0. I'm done here");
	} // end printToiletOptions
	void printDoorOptions() {
		System.out.println("\nWhat would you like to do at the door?"
						 + "\n 1. Turn doorknob"
						 + "\n 2. Peep through keyhole"
						 + "\n 3. Knock"
						 + "\n 0. I'm done here");
	} // end printDoorOptions
	void inspectSink() {
		System.out.println("\nThe sink has two handles. One is in disrepair while the other looks perfectly new.");
		if(gearOnHandle) {
			System.out.println("The gear sits on the newer handle.");
		}
		else {
			System.out.println("\nThe newer handle has strange notches in it, as if something used to be there...");
		}
		returningRoom = false;
		
		do {
			printSinkOptions();
			choice = Misc.validateInt(0, 2);
			
			switch(choice) {
			case 1: // Turn good handle
				if(hasGear) {
					if(hasDoorKey) {
						System.out.println("\nNothing happens when you try the handle once more."
										 + "\nThe number " + codeDigit + " remains carved into the drawer.");
					}
					else {
						System.out.println("\nYou notice that the gear fits perfectly onto the handle and that the whole handle turns with the gear."
										 + "\nAs you turn it, you hear a 'click' and find that the drawer is unlocked."
										 + "\nInside is a key, and the number " + codeDigit + " carved into the wood."
								 		 + "\nThese may be important."
								 		 + "\n(Obtained Door Key)");
						
						gearOnHandle = true;
						codeDigitDiscovered = true;
						hasDoorKey = true;
					}
				}
				else {
					System.out.println("You try the good handle and murky, green water runs from the tap. You quickly turn it off.");
				}
				break;
			case 2: // Turn bad handle
				System.out.println("You try the bad handle but it doesn't budge even a little. Now you look like an idiot.");
				break;
			case 0: // Return to bathroom
				returningRoom = true;
				break;
			}
		}while(!returningRoom);
		
	} // end inspectSink
	void inspectToilet() {
		if(hasGear) {
			System.out.println("\nDo you really want to stick your hand into the toilet again?");
		}
		else {
			System.out.println("\nYou approach the toilet and discover a large brown gear lodged in the bottom.");
			
			returningRoom = false;
			
			do {
				printToiletOptions();
				choice = Misc.validateInt(0, 2);
				
				switch(choice) {
				case 1: // Flush
					System.out.println("You try the handle but nothing happens. It must be broken.");
					break;
				case 2: // Grab gear
					if(hasGear) {
						System.out.println("\nDo you really want to stick your hand into the toilet again?");
					}
					else {
						System.out.println("\nYou dip you hand into the murky water and pull on the gear until it dislodges."
										 + "\nAs it turn out, it wasn't the gear that was brown..."
										 + "\n(Obtained Gear)");
						
						hasGear = true;
					}
					break;
				case 0: // Return to bathroom
					returningRoom = true;
					break;
				}
			}while(!returningRoom);
		}
	} // end inspectToilet
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
					if(hasDoorKey) {
						System.out.println("\nYou use the Door Key to unlock the door and quickly leave the bathroom."
										 + "\nYou are really starting to hate this house.");
						
						escaped = true;
						doorUnlocked = true;
						returningRoom = true;
						returningHall = true;
					}
					else {
						System.out.println("\nThe doorknob won't turn. It appears to be locked."
										 + "\nMaybe there's a key somewhere in the room.");
					}
				}
				break;
			case 2: // Peep through keyhole
				System.out.println("\nYou kneel down and peer through the keyhole."
								 + "\nYou see the hallway wall on the other side and wish you could join it.");
				break;
			case 3: // Knock
				if(knocked) {
					System.out.println("Maybe you shouldn't...");
				}
				else {
					System.out.println("\nYou rap your knuckles against the wood and wait."
									 + "\nThere's a loud thud from the other side.");
					
					knocked = true;
				}
				break;
			case 0: // Return to bathroom
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectDoor
	
} // end class Room2
