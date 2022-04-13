package parkingLotSpotPick;

public class Vehicle {
	private String owner;
	private int Size;
	private String plate;
	
	public Vehicle(String ownerName, int slotsNeeded, String index) {
		owner = ownerName;
		Size = slotsNeeded;
		plate = index;
	}
	public String getPlate() {
		return plate;
	}
	
	public String getOwnerName() {
		return owner;
	}
	
	public int getslotsNeeded() {
		return Size;
	}
	
}
