package parkingLotSpotPick;

import java.util.HashMap;
import java.util.LinkedList;

public class ParkingLot {
	private int rows;
	private int spots;
	private int levels;
	private int price;
	
	private parkingSlot parkingSlots [][][];
	private HashMap<String, LinkedList<parkingSlot>> parkingLotledger;
	
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
		
		parkingLotledger = new HashMap<String, LinkedList<parkingSlot>>();
		
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
	private LinkedList<parkingSlot> findSlotsInRow(parkingSlot row [], int slotsNeeded, int spot  ) {
		if (!row[spot].isEmpty()) {
			return null;
		}
		int i = spot - slotsNeeded;
		while (i<=spot) {
			i++;
			if (i<0 || (!row[i].isEmpty()))
				continue;
			int j = i;
			while (j<row.length && row[j].isEmpty()) {
				if (j-i >= slotsNeeded-1) {
					LinkedList<parkingSlot> slotsPicked = new LinkedList<parkingSlot>();  
					for (int k = i; k<=j;k++) {
						slotsPicked.add(row[k]);
					}
					return slotsPicked;
				}
				j++;
			}
			i=j;
		}
		return null;
	}
	//version which we search the entire row, so slot not specified
	private LinkedList<parkingSlot> findSlotsInRow(parkingSlot row [], int slotsNeeded ) {
		int i = -1;
		while (i<row.length-slotsNeeded) {
			i++;
			if (i<0 || (!row[i].isEmpty()))
				continue;
			int j = i;
			while (j<row.length && row[j].isEmpty()) {
				if (j-i >= slotsNeeded-1) {
					LinkedList<parkingSlot> slotsPicked = new LinkedList<parkingSlot>();  
					for (int k = i; k<=j;k++) {
						slotsPicked.add(row[k]);
					}
					return slotsPicked;
				}
				j++;
			}
			i=j;
		}
		return null;
	}
	//helper that iters the levels and rows
	private LinkedList<parkingSlot> findFirstAvaliable(Vehicle car) {
		for(int l = 0;l<levels;l++) {
			for(int r = 0;r<rows;r++) {
				LinkedList<parkingSlot> foundSlots = findSlotsInRow(parkingSlots[l][r], car.getslotsNeeded() );
				if(!(foundSlots==null)) {
					return foundSlots;					
				}
			}
		}
		return null;
	}
	
	public boolean park(Vehicle car, String ownerName) {
		LinkedList<parkingSlot> foundSlots =  findFirstAvaliable(car);
		if( foundSlots == null) {
			return false;
		}
		for (parkingSlot slot : foundSlots) {
			if(! slot.park(car)) {
				return false;
			}
		}
		parkingLotledger.put(ownerName, foundSlots);
		return true;
	}
	
	public boolean park(Vehicle car, String ownerName, int l, int r, int s ) {
		LinkedList<parkingSlot> foundSlots = findSlotsInRow(parkingSlots[l][r], car.getslotsNeeded(),s);
		if( foundSlots == null) {
			return false;
		}
		for (parkingSlot slot : foundSlots) {
			if(! slot.park(car)) {
				return false;
			}
		}
		parkingLotledger.put(ownerName, foundSlots);
		return true;
	}
	public boolean findOwner(String ownerName) {
		return parkingLotledger.containsKey(ownerName);
	}	
	
	public long leave(String ownerName) {
		if(findOwner(ownerName)) {
			LinkedList<parkingSlot> parkedSlots = parkingLotledger.get(ownerName);
			long parkedTime = -1;
			for (parkingSlot slot : parkedSlots) {
				parkedTime = slot.leave(ownerName);
				if(parkedTime<0) {
					return -1;
				}
			}
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