package parkingLotSpotPick;
import java.time.LocalTime; // import the LocalTime class
import java.time.Duration;

public class parkingTimer {
    LocalTime start;
    
	public parkingTimer() {
		start = LocalTime.now();
	}
	
    public long stop(){

        return Duration.between(start, LocalTime.now()).toSeconds();
    }
	
}
