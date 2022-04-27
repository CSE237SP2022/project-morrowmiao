package parkingLotSpotPick;

import java.util.HashMap;
import java.util.LinkedList;

public class ParkingLot {
	private int rows;
	private int spots;
	private int levels;
	private int price;
	
	
	
	private parkingSlot parkingSlots [][][];
	private HashMap<String, HashMap<String, LinkedList<parkingSlot>>> parkingLotledger;
	
	public LinkedList<String> ownersCars(String owner) {
		HashMap<String, LinkedList<parkingSlot>> cars = parkingLotledger.get(owner);
		LinkedList<String> carsplate = new LinkedList<String>();
		for (String key : cars.keySet()){
			carsplate.add(key);
		}
		return carsplate;
	}
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
		
		parkingLotledger = new HashMap<String, HashMap<String, LinkedList<parkingSlot>>>();
		
		parkingSlots = new parkingSlot[levels][rows][spots];
		for(int l = 0;l<levels;l++) {
			for(int r = 0;r<rows;r++) {
				for(int s = 0;s<spots;s++) {
					parkingSlots[l][r][s] = new parkingSlot(l,r,s);				
				}			
			}
		}
	}
	
	private LinkedList<parkingSlot> constructSlotsPicked (int start, int finish, parkingSlot row []){
		LinkedList<parkingSlot> slotsPicked = new LinkedList<parkingSlot>();  
		for (int k = start; k<=finish;k++) {
			slotsPicked.add(row[k]);
		}
		return slotsPicked;
	}
	//uses a sliding window to find if its possible to fit the car at spot location
	//I find it very hard to break up this function, due to the controls like continue and returns
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
					return constructSlotsPicked(i,j,row);
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
					return constructSlotsPicked(i,j,row);
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
		if(!this.findOwner(ownerName)) {
			this.addOwner(ownerName);
		}
		parkingLotledger.get(ownerName).put(car.getPlate(), foundSlots);
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
		if(!this.findOwner(ownerName)) {
			this.addOwner(ownerName);
		}
		parkingLotledger.get(ownerName).put(ownerName, foundSlots);
		return true;
	}
	
	public boolean findOwner(String ownerName) {
		return parkingLotledger.containsKey(ownerName);
	}	
	
	public boolean findCarbyOwner(String ownerName, String plate) {
		return parkingLotledger.get(ownerName).containsKey(plate);
	}
	public void addOwner(String ownerName) {
		parkingLotledger.put(ownerName, new HashMap<String, LinkedList<parkingSlot>>());
		return;
	}	

	private long leaveHelper(LinkedList<parkingSlot> parkedSlots, String ownerName) {
		long parkedTime = -1;
		for (parkingSlot slot : parkedSlots) {
			parkedTime = slot.leave(ownerName);
			if(parkedTime<0) {
				return -1;
			}
		}
		return parkedTime;
	}
	
	public long leave(String ownerName, String plate) {
		if(findCarbyOwner(ownerName,plate)) {
			LinkedList<parkingSlot> parkedSlots = parkingLotledger.get(ownerName).get(plate);
			long parkedTime = leaveHelper(parkedSlots, ownerName);
			if(parkedTime<0) {
				return -1;
			}else {
				parkingLotledger.get(ownerName).remove(plate);
			}
			if(parkingLotledger.get(ownerName).isEmpty()) {
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