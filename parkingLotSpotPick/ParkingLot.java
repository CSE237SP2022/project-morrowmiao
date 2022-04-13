package parkingLotSpotPick;

import java.util.HashMap;

public class ParkingLot {
	private int rows;
	private int spots;
	private int levels;
	private int price;
	
	private parkingSlot parkingSlots [][][];
	private HashMap<String, parkingSlot> parkingLotledger;
	
	public int toPossitive(int n) {
		if(n <=0)
			return 1;
		return n;
	}
	
	public ParkingLot(int nlevel, int nrow, int nslotN, int nprice) {

		levels = toPossitive(nlevel);
		rows = toPossitive(nrow);
		spots = toPossitive(nslotN);
		price = toPossitive(nprice);
		
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
	
	//uses a sliding window to find if its possible to fit the car at spot location
	private parkingSlot findSlotsInRow(parkingSlot row [], int slotsNeeded, int spot  ) {
		if (!row[spot].isEmpty()) {
			return null;
		}
		int i = spot - slotsNeeded;
		while (i<row.length) {
			i++;
			if (i<0 || (!row[spot].isEmpty()))
				continue;
			int j = i;
			while (row[j].isEmpty()) {
				if (j-i >= slotsNeeded-1) {
					return null;
				}
				j++;
			}
			i=j;
		}
		return null;
	}
	
	private parkingSlot findSlotsInRow(parkingSlot row [], int slotsNeeded ) {
		return null;
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
	
	public long leave(String ownerName) {
		if(findOwner(ownerName)) {
			parkingSlot slot = parkingLotledger.get(ownerName);
			long parkedTime = slot.leave(ownerName);
			if(parkedTime>0) {
				parkingLotledger.remove(ownerName);
			}
			return parkedTime;
		}
		return -1;
	}
	
	public long getPrice(long parkedTime) {
		return price*parkedTime;
	}
}
