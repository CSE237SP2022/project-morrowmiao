package parkingLotSpotPick;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class UnitTests {
	ParkingLot smallParking;
	ParkingLot bigParking;
	Vehicle car = new Vehicle("z", 1 ,"a");
	Vehicle car2 = new Vehicle("zz", 1 ,"aa");
	Vehicle van = new Vehicle("zz", 2, "b");
	Vehicle van2 = new Vehicle("zzz", 2, "bb");
	Vehicle bus = new Vehicle("zzz", 3 , "c");

	@Test
	public void ownerTest() {
		smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z");
		smallParking.park(van, "zz");
		String carOwner = "z";
		String vanOwner = "zz";

		assertTrue(smallParking.findOwner(carOwner));
		assertTrue(smallParking.findOwner(vanOwner));
		assertFalse(smallParking.findOwner("zzz"));

	}
	
	@Test
	public void carSizeTest() {
		smallParking = new ParkingLot(1,1,2,2) ;
		assertTrue(smallParking.park(car, "z"));
		assertTrue(smallParking.park(car2, "zz"));
		smallParking = new ParkingLot(1,1,2,2) ;
		assertTrue(smallParking.park(van, "zz"));
		smallParking = new ParkingLot(1,1,2,2) ;
		assertFalse(smallParking.park(bus, "zzz"));
	}

	@Test
	public void rowParkingTest() {
		smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z");
		smallParking.park(van, "zz");
		assertFalse(smallParking.park(bus, "zzz"));

		bigParking = new ParkingLot(1,2,5,2) ;
		bigParking.park(car, "z");
		bigParking.park(van, "zz");
		assertTrue(bigParking.park(bus, "zzz"));
	}
	
	@Test
	public void levelParkingTest() {
		bigParking = new ParkingLot(2,1,5,2) ;
		bigParking.park(car, "z");
		bigParking.park(van, "zz");
		assertTrue(bigParking.park(bus, "zzz"));
	}
	
	@Test
	public void leaveTest() {		
		smallParking = new ParkingLot(1,1,2,2);
		smallParking.park(car, "z");
		smallParking.park(car2, "zz");
		assertFalse(smallParking.park(van, "zz"));
		assertTrue(smallParking.leave("z", "a")>=0);
		assertTrue(smallParking.leave("zz", "aa")>=0);
		assertTrue(smallParking.park(van, "zz"));
	}
	
	@Test
	public void specificPark() {		
		smallParking = new ParkingLot(1,1,2,2);
		smallParking.park(car, "z");
		assertFalse(smallParking.park(car2, "zz",0,0,0));
		assertTrue(smallParking.park(car2, "zz",0,0,1));
		smallParking.leave("z", "a");
		smallParking.leave("zz", "aa");
		assertTrue(smallParking.park(car2, "zz",0,0,0));
	}
	
	
	@Test
	public void priceTest() {
		smallParking = new ParkingLot(1,1,5,2) ;
		smallParking.park(car, "z");
		smallParking.park(van, "zz");
		long carTimeCheck = smallParking.leave(car.getOwnerName(), "a");
		long carPrice = smallParking.getPrice(carTimeCheck);
		long carPriceCheck = carTimeCheck*2;
		assertEquals(carPrice,carPriceCheck);
		
	}
	
	@Test
	public void multipleCarTest() {
		Vehicle tank = new Vehicle("vito", 20, "t");

		bigParking = new ParkingLot(1,1,20,2) ;
		bigParking.park(car, "z");
		bigParking.park(van, "zz");
		bigParking.park(car2, "zz");
		bigParking.park(van2, "zzz");
		bigParking.park(bus, "zzz");
		bigParking.leave("z", car.getPlate());
		assertFalse(bigParking.park(tank, "vito"));
		bigParking.leave("zz", car2.getPlate());
		assertFalse(bigParking.park(tank, "vito"));
		bigParking.leave("zz", van.getPlate());
		assertFalse(bigParking.park(tank, "vito"));
		bigParking.leave("zzz", van2.getPlate());
		assertFalse(bigParking.park(tank, "vito"));
		bigParking.leave("zzz", bus.getPlate());
		assertTrue(bigParking.park(tank, "vito"));
		
	}
	
}
