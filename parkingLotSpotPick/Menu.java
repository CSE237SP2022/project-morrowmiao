package parkingLotSpotPick;
import support.cse131.ArgsProcessor;

public class Menu {
	int levels;
	int rows;
	int slots;
	int price;
	ArgsProcessor ap ;
	ParkingLot parking;

	private void InitMenu(String[] args) {
		ap = new ArgsProcessor(args);
		levels = ap.nextInt("Let's first setup the dimensions of your parking lot\n. How many levels do you want your parking lot to have?");
		rows = ap.nextInt("How many row would you like the parking lot to have for each floors?");
		slots = ap.nextInt("How many slots would you like for each row?");
		price = ap.nextInt("What price per second for the parking lot?");
		parking = new ParkingLot(levels,rows,slots,price) ;

	}
	
	//Since this function has mostly just prints and argprocessor inputs, 
	//in addition to that there are multiple breaks for the whileloop, it is better to not divide up the function.
	private void parkCar(String ownerName, String plate) {
		String input = "";
		int carSize = parking.toPossitive(ap.nextInt("How big is your car?"));
		if (plate.isEmpty()) {
			plate = ap.nextString("What is the license plate of your car");
		}
		Vehicle car = new Vehicle(ownerName, carSize, plate);
		while(true) {
			input = ap.nextString("Do you want to us to park your car for you?(Yes or No)");
			if (input.equals("Yes")) {
				if(parking.park(car, ownerName)) {
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
				
				if(parking.park(car, ownerName, l, r, s)) {
					System.out.println("You have successfully parked your car!");					
					break;
				}
				System.out.println("The slot you selected is occupied! Please try another");					
			}
		}	
	}
	
	private void carLeave(String ownerName, String plate) {
		String input =  ap.nextString("You parked your car here. Would you like to leave now?(Yes or No)");
		if (input.equals("Yes")) {
			long parkedTime = parking.leave(ownerName,plate);
			input  = "";
			while (!input.equals("Yes")) {
				input =  ap.nextString(String.format("Your car parked here for %d seconds, the total is $%d. Confirm payment?(Yes or No)",parkedTime,parking.getPrice(parkedTime) ));
			}					
			System.out.println("Have a nice day "+ ownerName);					
		}else {
			input =  ap.nextString("You parked your car here. Would you like to leave now?(Yes or No)");
			if (input.equals("Yes")) {
				
			}
		}
	}
	
	public void menuStart(String[] args) {

		InitMenu(args);

		//checks to see if the user wants to quit will check against the quit string
		while(!"quit".equals(ap.nextString("Type 'quit' if you would like to quit."))) {
			
			String ownerName = ap.nextString("What is your Name?");
			if(parking.findOwner(ownerName)) {
				String parkedCars = parking.ownersCars(ownerName).toString();
				String plate =  ap.nextString("You parked " +parkedCars+ " here before. What is your car's plate number");
				if(parking.findCarbyOwner(ownerName, plate)) {
					carLeave(ownerName,plate);
				}else {
					parkCar(ownerName,plate);
				}
			}
			else{
				parkCar(ownerName,"");
			}
		}
	}

}