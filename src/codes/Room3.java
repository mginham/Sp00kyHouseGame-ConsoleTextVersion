/*
 * Project:		Sp00ky House (console text version)
 * Program:		Room3 (living room)
 * Author:		Megan Ginham
 * Date:		December 21, 2018
 * 
 * How to find codeDigit:
 *   1. inspect couch, turn over left cushion to discover the remote
 *	 2. inspect the lamp, turn it off, inspect lampshade to reveal the correct TV channel
 *	 3. inspect TV, turn it on, change the channel to correct TV channel, inspect screen to reveal the codeDigit
 */

package codes;

public class Room3 extends Room {

	// Global variables //
	static boolean hasRemote;
	static boolean lightOn, tvOn;
	static boolean correctChannel;
	static int channel, correctChannelNum;
	
	// Constructor //
	public Room3(int[] frontDoorCode) {
		reset(frontDoorCode);
	} // end constructor

	// Methods //
	static void reset(int[] frontDoorCode) {
		codeDigit = frontDoorCode[2];
		codeDigitDiscovered = false;
		
		escaped = false;
		returningRoom = false;
		returningHall = false;
		
		hasRemote = false;
		lightOn = true;
		tvOn = false;
		correctChannel = false;
		
		channel = -1;
		correctChannelNum = Misc.generateCode(111, 999);
	} // end reset
	@Override
	void printStart() {
		System.out.println("\nYou leave the hallway and enter the room on the left");
	} // end printStart
	@Override
	void printDesc() {
		System.out.println("\nIt's a living room."
						 + "\nYou see a couch, a TV, and a lamp. The lamp in the corner illuminates the room.");
	} // end printDesc
	@Override
	void printOptions() {
		System.out.println("\nWhat would you like to do?"
						 + "\n 1. Inspect couch"
						 + "\n 2. Inspect TV"
						 + "\n 3. Inspect lamp"
						 + "\n 0. Return to hallway");
	} // end printOptions
	void printCouchOptions() {
		System.out.println("\nWhat would you like to do at the couch?"
						 + "\n 1. Look beneath it"
						 + "\n 2. Turn left cusion"
						 + "\n 3. Turn right cusion"
						 + "\n 0. I'm done here");
	} // end printCouchOptions
	void printLampOptions() {
		System.out.print("\nWhat would you like to do at the lamp?");
		if(lightOn) {
		System.out.print("\n 1. Turn light off");
		}
		else {
		System.out.print("\n 1. Turn light on");
		}
		System.out.println("\n 2. Inspect lamp shade"
						 + "\n 0. I'm done here");
	} // end printLampOptions
	void printTVOptions() {
		System.out.print("\nWhat would you like to do at the TV?");
		if(tvOn) {
			System.out.print("\n 1. Turn TV off");
		}
		else {
			System.out.print("\n 1. Turn TV on");
		}
		System.out.println("\n 2. Inspect screen"
						 + "\n 3. Change channel"
						 + "\n 0. I'm done here");
	} // end printTVOptions
	void inspectCouch() {
		returningRoom = false;
		
		do {
			printCouchOptions();
			choice = Misc.validateInt(0, 3);
			
			switch(choice) {
			case 1: // Look beneath couch
				System.out.println("There are dust bunnies and little else.");
				break;
			case 2: // Turn left cushion (where remote is hidden)
				if(hasRemote) {
					System.out.println("Were you expecting to find something else?");
				}
				else {
					System.out.println("You find a remote."
								  + "\n(Obtained TV Remote)");
					hasRemote = true;
				}
				break;
			case 3: // Turn right cushion
				System.out.println("You see a large, brownish-yellow stain beneath the cushion and wonder if you should wash your hands.");
				break;
			case 0: // Return to livingRoom
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectCouch
	void inspectTV() {
		returningRoom = false;
		
		do {
			printTVOptions();
			choice = Misc.validateInt(0, 3);
			
			switch(choice) {
			case 1: // Toggle TV
				if(hasRemote) {
					if(tvOn) {
						tvOn = false;
					}
					else {
						tvOn = true;
					}
				}
				else {
					System.out.println("There don't seem to be any buttons. Maybe there's a remote lying around somewhere.");
				}
				break;
			case 2: // Inspect screen
				if(tvOn) {
					if(correctChannel) {
						System.out.println("The number '" + codeDigit + "' flickers on an otherwise black screen.");
						codeDigitDiscovered = true;
					}
					else {
						System.out.println("Nothing but static.");
					}
				}
				else {
					System.out.println("The TV isn't on.");
				}
				break;
			case 3: // Change channel
				if(hasRemote) {
					if(tvOn) {
						System.out.println("What channel would you like to change to?");
						channel = Misc.validateInt(0, 2147483647); // max = max int value
						
						if(channel == correctChannelNum) {
							correctChannel = true;
						}
						else {
							correctChannel = false;
						}
					}
					else {
						System.out.println("The TV isn't on.");
					}
				}
				else {
					System.out.println("There don't seem to be any buttons. Maybe there's a remote lying around somewhere.");
				}
				break;
			case 0: // Return to livingRoom
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectTV
	void inspectLamp() {
		returningRoom = false;
		
		do {
			printLampOptions();
			choice = Misc.validateInt(0, 2);
			
			switch(choice) {
			case 1: // Toggle lamp
				if(lightOn) {
					lightOn = false;
				}
				else {
					lightOn = true;
				}
				break;
			case 2: // Inspect lamp shade
				if(lightOn) {
					System.out.println("The lamp shade is torn in a few places and covered in dust.");
				}
				else {
					System.out.println("Amidst the darkness, a small '" + correctChannelNum + "' glows in flourescent paint on the back of the lamp shade.");
				}
				break;
			case 0: // Return to livingRoom
				returningRoom = true;
				break;
			}
		} while(!returningRoom);
	} // end inspectLamp

} // end class Room3