/*
 * Project:		Sp00ky House (console text version)
 * Program:		Hall
 * Author:		Megan Ginham
 * Date:		December 21, 2018
 */

package codes;

public class Hall {
	
	// Global variables //
	static int[] frontDoorCode = new int[4];
	static boolean escaped, crackedCode;
	static char response = 0;
	
	// Constructor //
	public Hall(int[] frontDoorCode) {
		reset(frontDoorCode);
	} // end constructor
	
	// Methods //
	static void reset(int[] frontDoorCode) {
		setFrontDoorCode(frontDoorCode);
		
		escaped = false;
		crackedCode = false;
	} // end reset
	void printStart() {
		System.out.println("\nYou exit the bedroom and find yourself in the hallways of what seems to be a regular house."
						 + "\nTo your left is a large open archway into another room."
						 + "\nTo your right there is a door and a narrow archway."
						 + "\nStraight ahead is what seems to be the front door.");
	} // end printStart
	void printOptions(boolean bathroomDiscovered, boolean livingRoomDiscovered, boolean kitchenDiscovered) {
		System.out.print("\nWhat would you like to do?"
					   + "\n 1. Inspect the front door"
					   + "\n 2. Return to bedroom");
		
		if(livingRoomDiscovered) {
			System.out.print("\n 3. Return to living room");
		}
		else {
			System.out.print("\n 3. Enter room to the left");
		}
		if(bathroomDiscovered) {
			System.out.print("\n 4. Return to bathroom");
		}
		else {
			System.out.print("\n 4. Inspect door to the right");
		}
		if(kitchenDiscovered) {
			System.out.println("\n 5. Return to kitchen");
		}
		else {
			System.out.println("\n 5. Enter room to the right");
		}
	} // end printOptions
	void inspectFrontDoor() {
		System.out.println("\nThe door is locked, but it has an electronic keypad. Looks like a four-digit code could unlock it."
				 		 + "\nWould you like to try a combination? (y/n)");
		response = Misc.validateChar();
		
		switch(response) {
		case 'y':
			crackedCode = Misc.enterDoorCode(frontDoorCode);
			
			if(crackedCode) {
				System.out.println("\nYou hear gears snap as the electronic lock glows green. You've unlocked the door!");
				escaped = true;
			}
			else {
				System.out.println("\nYou hear a shrill 'beep' as the electronic lock flashes red. The door remains locked.");
				escaped = false;
			}
			
			break;
		case 'n': // Nothing needs to happen
			break;
		default:
			System.out.println("Sorry, that's not an option.");
			break;
		}
	} // end inspectFrontDoor
	void exitHouseMessage() {
		System.out.println("\nOpening the door wide, you're partially blinded by the brightness of day."
						 + "\nYou raise an arm to shield your eyes while they adjust."
						 + "\nYour heart races with joy and relief as you take slow steps out of this trap and into the world."
						 + "\nThen you hear clapping."
						 + "\nWhen you lower your arm, you're surrounded by men and women in lab coats."
						 + "\nYou realize that the light isn't from the sun, but from massive floodlights positioned around you."
						 + "\nYou hear a whistle. \"Alright boys, good job. Let's run it again.\""
						 + "\nAnd then the world goes black.");
	} // exitHouse
	
	// Getters & Setters //
	protected static void setFrontDoorCode(int[] newFrontDoorCode) {
		frontDoorCode = newFrontDoorCode;
	} // end setFrontDoorCode
} // end class Hall
