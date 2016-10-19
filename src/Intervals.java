import java.util.Scanner;
import org.jfugue.player.Player;
import java.util.ArrayList;
import java.util.Random;

public class Intervals extends Trainer{
	private Random rand;
	private String[] intervals;
	private String[] chromatic;
	private ArrayList<String> quizList;
	
	public Intervals() {
		super();
		rand = new Random();
		intervals = super.intervals;
		chromatic = super.chromatic;
	}
	
	private boolean validInterval(String interval) {
		for(int i = 0; i < intervals.length; i++) {
			if(intervals[i].equals(interval)) {
				return true;
			}
		}
		
		return false;
	}
	
	private String play(int root, String interval) {
		String rootPitch = chromatic[root];
		int halfsteps = super.intervalToHalfSteps(interval);
		String upperPitch = super.getIntervalPitch(root, halfsteps);
		
		// Prevent interval pitch from being lower than root by playing the higher octave when appropriate.
		if(root + halfsteps < 12) {
			System.out.println(rootPitch + "5w " + upperPitch + "5w ");
			return rootPitch + "5w " + upperPitch + "5w ";	
		} else {
			System.out.println(rootPitch + "5w " + upperPitch + "6w ");
			return rootPitch + "5w " + upperPitch + "6w ";
		}
	}
	
	private void startQuizing(Scanner scanner, Player player) {
		System.out.println("Type \"Ready\" to play an interval. Type \"Done\" to return to the main menu.");
		
		String response = scanner.next(), answer = ""; // Response of the user and the correct answer
		int root, interval; // Random indexes of the array containing all pitches and the arrayList containing quizable intervals
		
		while(!(response.equals("Done"))) {			
			// Generates a random number between 0 and 1200 and then mods it by 12. Resulting number is is the index of the root that will be played.
			root = rand.nextInt(1201) % 12;
			
			// Generates a random number between 0 and quizList.size() * 100 and then mods it by the length of the list.
			// Resulting number is the index of the interval to be played.
			interval = rand.nextInt((quizList.size() * 100) + 1) % quizList.size();
			
			// Gets the correct answer from the list of quizable intervals.
			answer = quizList.get(interval);
			
			// Plays the interval. Would be really cluttered if I put it in here, so I made it its own function.
			player.play(play(root, answer));
			
			System.out.print("What interval was just played? ");			
			response = scanner.next();
			
			while(!(response.equals(answer)) && !(response.equals("Skip"))) {
				System.out.print("That was not the correct interval. Try again or type \"Skip\" to skip: ");
				
				response = scanner.next();
			}
			
			System.out.println("Type \"Ready\" to play an interval. Type \"Done\" to return to the main menu.");
			response = scanner.next();
		}
	}
	
	public void printIntervals() {
		for(int i = 0; i < intervals.length; i++) {
			System.out.print(intervals[i] + " ");
		}
		System.out.println("\n");
	}
	
	public void act(Scanner scanner, Player player) {
		quizList = new ArrayList<String>();
		
		// Welcome
		System.out.println("Welcome to the Interval Training mode.\n");
		
		// Instructions for giving valid intervals
		System.out.println("Please type in the intervals you would like to be quized on one at a time.");
		System.out.println("To finish putting in intervals, type \"Done\".");
		System.out.println("Press Q to quit and return to the main menu.");
		System.out.println("If your would like to be quizzed on all intervals in a scale, type \"All\".\n");
		
		System.out.print("Intervals should be be ");
		printIntervals();
		
		// Start to take in intervals
		System.out.print("Enter interval: ");
		String interval = scanner.next();
		
		while(!(interval.equals("Done")) && !(interval.equals("Q"))) {			
			// If "All", then include all intervals and move onto quizing
			if(interval.equals("All")) {
				quizList.clear();
				quizList.add("m2");
				quizList.add("M2");
				quizList.add("m3");
				quizList.add("M3");
				quizList.add("P4");
				quizList.add("P5");
				quizList.add("m6");
				quizList.add("M6");
				quizList.add("m7");
				quizList.add("M7");
				
				break;
			}
			
			// Check if the given interval is valid, then add it to the list if it is not already there
			if(validInterval(interval)) {
				if(!quizList.contains(interval)) {
					quizList.add(interval);
				} else {
					System.out.println("This interval is already in the list.");
				}
			} else {
				System.out.println("This is not a valid interval.");
				System.out.print("The list of valid intervals are: ");
				printIntervals();
			}
			
			// Keep loop going
			System.out.print("Enter interval: ");
			interval = scanner.next();
		}
		
		// Start quizzing if the last response given when looking for intervals was not "Q"
		if(!(interval.equals("Q"))) {
			System.out.print("\nYou will be quizzed on the following: ");
			for(int i = 0; i < quizList.size(); i++) {
				System.out.print(quizList.get(i) + " ");
			}
			System.out.println("\n");
			
			startQuizing(scanner, player);
		}
	}
}