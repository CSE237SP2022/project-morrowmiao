package parkingLotSpotPick;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class UnitTests {
	ParkingLot smallParking;
	ParkingLot bigParking;
	Vehicle car = new Vehicle("z", 1 ,"a");
	Vehicle van = new Vehicle("a", 2, "b");
	Vehicle bus = new Vehicle("b", 3 , "c");

	@Test
	public void ownerTest() {
		ParkingLot smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z", "ad");
		smallParking.park(van, "a", "ac");
		String carOwner = "z";
		String vanOwner = "a";
		assertEquals(carOwner, car.getOwnerName());
		assertEquals(vanOwner, van.getOwnerName());
		
	}
	

	@Test
	public void rowParkingTest() {
		ParkingLot smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z","a");
		
		
		
	}
	public void fullTest() {		
		ParkingLot smallParking = new ParkingLot(1,1,2,2) ;
		smallParking.park(car, "z","a");
		assertEquals(smallParking.park(van, "a", "b"),false);
		ParkingLot smallParkingTwo = new ParkingLot(1,1,4,2) ;
		smallParkingTwo.park(car, "z","a");
		smallParkingTwo.park(van, "a", "ac");
		assertEquals(smallParkingTwo.park(bus, "b", "c"),false);
	}
	
	
	
	@Test
	public void priceTest() {
		ParkingLot smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z","a");
		smallParking.park(van, "a", "b");
		long carTimeCheck = smallParking.leave(car.getOwnerName());
		long carPrice = smallParking.getPrice(carTimeCheck);
		long carPriceCheck = carTimeCheck*2;
		assertEquals(carPrice,carPriceCheck);
		
	}
}
