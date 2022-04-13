package parkingLotSpotPick;

import java.util.Arrays;

import support.cse131.ArgsProcessor;

public class parkingLotProgram {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		String quit = "quit";
		String insert = "insert";
		String remove = "remove";
		String spot = "X";
		String empty = " ";
		int levels = ap.nextInt("Let's first setup the dimensions of your parking lot\n. How many levels do you want your parking lot to have?");
		int rows = ap.nextInt("How many row would you like the parking lot to have for each floors?");
		int slots = ap.nextInt("How many slots would you like for each row?");
		int price = ap.nextInt("What price per second for the parking lot?");
		int x = 0;
		int y = 0;
		String input = "";
		ParkingLot parking = new ParkingLot(levels,rows,slots,price) ;
		//checks to see if the user wants to quit will check against the quit string
		while(!quit.equals(ap.nextString("Type quit if you would like to quit."))) {
			
			String name = ap.nextString("What is your Name?");
			if(parking.findOwner(name)) {
				input =  ap.nextString("You parked your car here. Would you like to leave now?(Yes or No)");
				if (input.equals("Yes")) {
					long parkedTime = parking.leave(name);
					input  = "";
					while (!input.equals("Yes")) {
						input =  ap.nextString(String.format("Your car parked here for %d seconds, the total is $%d. Confirm payment?(Yes or No)",parkedTime,parking.getPrice(parkedTime) ));
					}					
					System.out.println("Have a nice day "+ name);					
				}
			}
			//checks to see if the user wants to remove by checking the input against the remove string
			else{
				int carSize = parking.toPossitive(ap.nextInt("How big is your car?"));
				Vehicle car = new Vehicle(name, carSize);//size doesnt matter for now
				while(true) {
					input = ap.nextString("Do you want to us to park your car for you?(Yes or No)");
					if (input.equals("Yes")) {
						if(parking.park(car, name)) {
							System.out.println("You have successfully parked your car!");					
						}else {
							System.out.println("The parking lot cannot fit your car!");											
						}
						break;
					}else {
						int l = ap.nextInt("Which floor would you like to park?")-1;
						int r = ap.nextInt("Which row would you like to park?")-1;					
						int s = ap.nextInt("Which slot would you like to park?")-1;
						while(l>=levels||r>=rows||s>=slots||l<0||r<0||s<0) {
							System.out.println("Please enter valid values");
							l = ap.nextInt("Which floor would you like to park?")-1;
							r = ap.nextInt("Which row would you like to park?")-1;					
							s = ap.nextInt("Which slot would you like to park?")-1;
						}
						
						if(parking.park(car, name, l, r, s)) {
							System.out.println("You have successfully parked your car!");					
							break;
						}
						System.out.println("The slot you selected is occupied! Please try another");					
					}
					
				}				
			}
		}
	}

}