package parkingLotSpotPick;

public class parkingSlot {
	private Vehicle parkedCar;
	private int level;
	private int row;
	private int slotN;
	private parkingTimer timer;
	
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
        	timer = new parkingTimer();
        	return true;    		
    	}
    	return false;
    }
    public long leave(String ownerName) {
    	if ((!this.isEmpty()) && parkedCar.getOwnerName().equals(ownerName)) {
        	parkedCar = null;  
        	return timer.stop();
    	}
		return -1;
    }
    

}
