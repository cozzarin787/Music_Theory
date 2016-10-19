public class Trainer {
	
	public String[] chromatic = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
	
	public String[] intervals = {"", "", "m2", "M2", "m3", "M3", "P4", "", "P5", "m6", "M6", "m7", "M7"};
	
	public Trainer() {
		
	}
	
	public String halfStepsToInterval(int halfsteps) {
		return intervals[halfsteps];
	}
	
	public String getIntervalPitch(int root, int halfsteps) {
		return chromatic[(root + halfsteps) % 12];
	}
	
	public int intervalToHalfSteps(String interval) {
		for(int i = 2; i < intervals.length; i++) {
			if(intervals[i].equals(interval)) {
				return i-1;
			}
		}
		
		return -1; // Should never happen
	}
}