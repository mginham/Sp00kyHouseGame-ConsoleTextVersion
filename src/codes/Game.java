/*
 * Project:		Sp00ky House (console text version)
 * Program:		Game
 * Author:		Megan Ginham, Christopher Mohan, Zach Raposo, Elliot Lam
 * Date:		December 21, 2018
 */

package codes;

public class Game {
	
	// Global variables //
	static int[] frontDoorCode = new int [4];
	static int choice = -1;
	static boolean play = true;
	static boolean bedroomDiscovered = false, hallDiscovered = false, bathroomDiscovered = false, livingRoomDiscovered = false, kitchenDiscovered = false;
	static boolean returningHall = false, escaped = false;
	
	static Hall hall;
	static Room1 bedroom;
	static Room2 bathroom;
	static Room3 livingRoom;
	static Room4 kitchen;

	// Methods //
	public static void setUpGame() {
		frontDoorCode = Misc.generateCode(4, 9, 0);
		
		hall = new Hall(frontDoorCode);
		bedroom = new Room1(frontDoorCode);
		bathroom = new Room2(frontDoorCode);
		livingRoom = new Room3(frontDoorCode);
		kitchen = new Room4(frontDoorCode);
	} // end setUpGame
	public static void resetGame() {
		frontDoorCode = Misc.generateCode(4, 9, 0);
		
		bedroomDiscovered = false;
		hallDiscovered = false;
		bathroomDiscovered = false;
		livingRoomDiscovered = false;
		kitchenDiscovered = false;
		
		returningHall = false;
		escaped = false;
		
		Hall.reset(frontDoorCode);
		Room1.reset(frontDoorCode);
		Room2.reset(frontDoorCode);
		Room3.reset(frontDoorCode);
		Room4.reset(frontDoorCode);
	} // end resetGame
	public static void beginGame() {
		choice = Menu.mainMenu();
		
		if(choice == 0) { // quit game
			System.out.println("Are you sure you want to quit? (1: no, 0: yes)");
			choice = Misc.validateInt(0, 1);
				
				if(choice == 0)
					play = false;
				else
					play = true;
		}
		else { // play game
			System.out.println("\n//======================================================//");
					
			play = true;
		}
	} // end beginGame
	public static void endGame() {
		play = Menu.endMenu(play);
	} // end endGame
	public static void runGame() {
		enterBedroom();
		enterHall();
	} // end runGame
	public static void enterHall() {
		if(!hallDiscovered) {
			hall.printStart();
			hallDiscovered = true;
		}
		
		do {
			hall.printOptions(bathroomDiscovered, livingRoomDiscovered, kitchenDiscovered);
			choice = Misc.validateInt(1, 5);
			
			switch (choice) {
			case 1:
				hall.inspectFrontDoor();
				break;
			case 2:
				enterBedroom();
				break;
			case 3:
				enterLivingRoom();
				break;
			case 4:
				enterBathroom();
				break;
			case 5:
				enterKitchen();
				break;
			case -1: // TODO: delete
				System.out.println("frontDoorCode:" + frontDoorCode[0] + "" + frontDoorCode[1] + ""  + frontDoorCode[2] + ""  + frontDoorCode[3]);
				break;
			}
			
			escaped = hall.escaped;
		} while(!escaped);
		
	} // end enterHall
	public static void enterBedroom() {
		if(!bedroomDiscovered) { // only print start the first time
			bedroom.printStart();
			bedroomDiscovered = true;
		}
		
		bedroom.printDesc();
		returningHall = false;
		
		do {
			bedroom.printOptions();
			choice = Misc.validateInt(-1, 4); // TODO: change back to (1,4)
			
			switch(choice) {
			case 1:
				bedroom.inspectBed();
				break;
			case 2:
				bedroom.inspectNightStand();
				break;
			case 3:
				bedroom.inspectDoor();
				break;
			case 4:
				bedroom.inspectRug();
				break;
			case -1: // TODO: delete
				bedroom.returningHall = true;
				break;
			}
			
			returningHall = bedroom.returningHall;
		} while(!returningHall);
	} // end enterBedroom
	public static void enterBathroom() {
		if(!bathroomDiscovered) {
			bathroom.printStart();
			bathroomDiscovered = true;
		}
		
		bathroom.printDesc();
		bathroom.returningHall = false;
		
		do {
			bathroom.printOptions();
			choice = Misc.validateInt(1, 3);
			
			switch(choice) {
			case 1: // Inspect sink
				bathroom.inspectSink();
				break;
			case 2: // Inspect toilet
				bathroom.inspectToilet();
				break;
			case 3: // Inspect Door
				bathroom.inspectDoor();
				break;
			}
			
			returningHall = bathroom.returningHall;
		} while(!returningHall);
		
	} // end enterBedroom
	public static void enterLivingRoom() {
		if(!livingRoomDiscovered) {
			livingRoom.printStart();
			livingRoomDiscovered = true;
		}
		
		livingRoom.printDesc();
		returningHall = false;
		
		do {
			livingRoom.printOptions();
			choice = Misc.validateInt(0, 3);
			
			switch(choice) {
			case 1:
				livingRoom.inspectCouch();
				break;
			case 2:
				livingRoom.inspectTV();
				break;
			case 3:
				livingRoom.inspectLamp();
				break;
			case 0:
				returningHall = true;
				break;
			}
		} while(!returningHall);
	} // end enterBedroom
	public static void enterKitchen() {
		kitchen.printDesc();
		returningHall = false;
		
		if(!kitchenDiscovered) {
			kitchen.printStart();
			kitchenDiscovered = true;
		}
		
		do {
			kitchen.printOptions();
			choice = Misc.validateInt(0, 3);
			
			switch(choice) {
			case 1:
				kitchen.inspectCupboard();
				break;
			case 2:
				kitchen.inspectFridge();
				break;
			case 3:
				kitchen.inspectOven();
				break;
			case 0:
				returningHall = true;
				break;
			}
		} while(!returningHall);
	} // end enterKitchen
	
	// MAIN //
	public static void main(String[] args) {
		setUpGame();
		
		beginGame();
		
		while(play) {
			resetGame();
			runGame();
			endGame();
		}
		
		System.out.println("\n//================Thank you for playing!================//");
	} // end main

} // end class Game
