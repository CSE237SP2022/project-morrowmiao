package parkingLotSpotPick;

import java.util.HashMap;

public class ParkingLot {
	private int rows;
	private int spots;
	private int levels;
	
	private parkingSlot parkingSlots [][][];
	private HashMap<String, parkingSlot> parkingLotledger;
	
	public ParkingLot(int nlevel, int nrow, int nslotN) {
		if(nlevel <=0)
			nlevel =1;
		if(nrow <=0)
			nrow =1;
		if(nslotN <=0)
			nslotN =1;		
		
		levels = nlevel;
		rows = nrow;
		spots = nslotN;
		parkingLotledger = new HashMap<String, parkingSlot>();
		
		parkingSlots = new parkingSlot[levels][rows][spots];
		for(int l = 0;l<levels;l++) {
			for(int r = 0;r<rows;r++) {
				for(int s = 0;s<spots;s++) {
					parkingSlots[l][r][s] = new parkingSlot(l,r,s);				
				}			
			}
		}
	}
	
	private parkingSlot findFirstAvaliable() {
		for(int l = 0;l<levels;l++) {
			for(int r = 0;r<rows;r++) {
				for(int s = 0;s<spots;s++) {
					if (parkingSlots[l][r][s].isEmpty())
						return parkingSlots[l][r][s];
				}			
			}
		}
		return null;
	}
	
	public boolean park(Vehicle car, String ownerName) {
		parkingSlot emptySlot = findFirstAvaliable();
		if( emptySlot == null) {
			return false;
		}
		if(emptySlot.park(car)) {
			parkingLotledger.put(ownerName, emptySlot);
			return true;
		}
		return false;
	}
	
	public boolean park(Vehicle car, String ownerName, int l, int r, int s ) {
		if(parkingSlots[l][r][s].park(car)) {
			parkingLotledger.put(ownerName, parkingSlots[l][r][s]);
			return true;
		}
		return false;
	}
	public boolean findOwner(String ownerName) {
		return parkingLotledger.containsKey(ownerName);
	}	
	
	public boolean leave(String ownerName) {
		if(findOwner(ownerName)) {
			parkingSlot slot = parkingLotledger.get(ownerName);
			if(slot.leave(ownerName)) {
				parkingLotledger.remove(ownerName);
				return true;
			}
			return false;
		}
		return false;
	}
}
