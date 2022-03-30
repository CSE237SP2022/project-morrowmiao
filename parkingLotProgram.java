package parkingLotSpotPick;
import support.cse131.ArgsProcessor;
public class parkingLotProgram {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		package parkingLotSpotPick;
import support.cse131.ArgsProcessor;

public class parkingLotProgram {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArgsProcessor ap = new ArgsProcessor(args);
		String quit = "quit";
		String insert = "insert";
		String remove = "remove";
		String spot = "X";
		String empty = " ";
		int width = ap.nextInt("How wide would you like the parking lot to be?");
		int height = ap.nextInt("How tall would you like the parking lot to be?");
		int x = 0;
		int y = 0;
		String [] [] parkingLot = new String [width] [height];
		//checks to see if the user wants to quit will check against the quit string
		while(!quit.equals(ap.nextString("Type quit if you would like to quit."))) {
			quit = ap.nextString("Would you like to remove or insert a car");
			//checks to see if the user wants to remove by checking the input against the remove string
			if(quit.equals(remove)){
				x = ap.nextInt("What X position would you like to remove?");
				y = ap.nextInt("What Y position would you like to remove?");
				while(x>width||y>height||x<0||y<0) {
					System.out.println("Please enter valid X and Y values");
					x = ap.nextInt("What X position would you like to remove?");
					y = ap.nextInt("What Y position would you like to remove?");
				}
				while(parkingLot[x][y].equals(empty)) {
					System.out.println("That spot is already empty please enter valid values.");
					x = ap.nextInt("What X position would you like to insert?");
					y = ap.nextInt("What Y position would you like to insert?");
				}
				parkingLot[x][y] = " ";
			}
			//checks to see if the user wants to remove by checking the input against the insert string
			else if(quit.equals(insert)) {
				x = ap.nextInt("What X position would you like to insert?");
				y = ap.nextInt("What Y position would you like to insert?");
				//checks to see if the x and y user input is valid and inputs an X at
				while(x>width||y>height||x<0||y<0) {
					System.out.println("Please enter valid X and Y values");
					x = ap.nextInt("What X position would you like to insert?");
					y = ap.nextInt("What Y position would you like to insert?");
				}
				while(parkingLot[x][y].equals(spot)) {
					System.out.println("That spot is already taken please enter valid values.");
					x = ap.nextInt("What X position would you like to insert?");
					y = ap.nextInt("What Y position would you like to insert?");
				}
				parkingLot[x][y] = "X";
			}
			//tells the user they didn't select a valid input
			else {
				System.out.println("Please select to remove or insert a car or to quit.");
			}
		}
	}

}

	}

}
