package parkingLotSpotPick;

public class parkingSlot {
	private Vehicle parkedCar;
	private int level;
	private int row;
	private int slotN;
	
	public parkingSlot(int nlevel, int nrow, int nslotN) {
		level = nlevel;
		row = nrow;
		slotN = nslotN;
		
	}
	
    public int getLevel() {
    	return level;
    }
    public int getRow() {
    	return row;
    }
    public int getSlotN() {
    	return slotN;
    }
	
    public boolean isEmpty(){
        return parkedCar == null;
    }
    
    public boolean park(Vehicle car) {
    	if (this.isEmpty()) {
        	parkedCar = car;
        	return true;    		
    	}
    	return false;
    }
    public boolean leave(String ownerName) {
    	if ((!this.isEmpty()) && parkedCar.getOwnerName().equals(ownerName)) {
        	parkedCar = null;
        	return true;
    	}
		return false;
    }
    

}
