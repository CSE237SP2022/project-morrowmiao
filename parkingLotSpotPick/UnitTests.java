package parkingLotSpotPick;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTests {
	ParkingLot smallParking;
	ParkingLot bigParking;
	Vehicle car = new Vehicle("z", 1);
	Vehicle van = new Vehicle("a", 2);
	Vehicle bus = new Vehicle("b", 3);

	@Test
	void ownerTest() {
		ParkingLot smallParking = new ParkingLot(1,1,5,2) ;
		assertFalse(smallParking.findOwner("z"));
		assertFalse(smallParking.park(car, "a"));

		smallParking.park(car, "z");
		smallParking.park(van, "a");
		assertTrue(smallParking.findOwner("z"));
		assertTrue(smallParking.findOwner("a"));
		assertFalse(smallParking.findOwner("b"));
	}
	

	@Test
	void rowParkingTest() {
		ParkingLot smallParking = new ParkingLot(1,1,5,2) ;
		assertTrue(smallParking.park(car, "z"));
		assertTrue(smallParking.park(van, "a"));
		smallParking.park(van, "a");

	}
	
	
	
	@Test
	void priceTest() {

		assertEquals(checker.getSize(),4);
	
		assertTrue(checker.check("eat"));
	}
}
