package parkingLotSpotPick;

public class Vehicle {
	private String owner;
	private int Size;
	
	public Vehicle(String ownerName, int slotsNeeded) {
		owner = ownerName;
		Size = slotsNeeded;
	}
	
	public String getOwnerName() {
		return owner;
	}
	
	public int getslotsNeeded() {
		return Size;
	}
	
}
