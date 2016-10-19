import java.util.Scanner;
import org.jfugue.player.Player;
import java.util.ArrayList;
import java.util.Random;

public class Triads extends Trainer{
	private Random rand;
	private String[] chromatic;
	private String[] triads = {"d", "m", "M", "A"};
	private ArrayList<String> quizList;
	
	public Triads() {
		super();
		rand = new Random();
		chromatic = super.chromatic;
	}
	
	public void play(int root, String triad, Player player) {
		String rootPitch = chromatic[root] + "5";
		int thirdInterval = 0, fifthInterval = 0;
		
		switch (triad) {
			case "d":
				thirdInterval = 3; fifthInterval = 6;				
				break;
			case "m":
				thirdInterval = 3; fifthInterval = 7;				
				break;
			case "M":
				thirdInterval = 4; fifthInterval = 7;				
				break;
			case "A":
				thirdInterval = 4; fifthInterval = 8;
				break;
			default:
				// Should not happen
				break;
		}
		
		String thirdPitch = super.getIntervalPitch(root, thirdInterval);
		String fifthPitch = super.getIntervalPitch(root, fifthInterval);
		
		if(root + thirdInterval < 12) { 
			thirdPitch += "5";
		} else {
			thirdPitch += "6";
		}
		
		if(root + fifthInterval < 12) {
			fifthPitch += "5";
		} else {
			fifthPitch += "6";
		}
		
		player.play(rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w " + rootPitch + "h " + thirdPitch + "h " + fifthPitch + "h " + rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w");	
	}
	
	public void startQuizing(Scanner scanner, Player player) {
		System.out.println("Type \"Ready\" to play a triad. Type \"Done\" to return to the main menu.");

		String response = scanner.next(), answer = "";
		int triad, root;
		
		while(!(response.equals("Done"))) {
			// Random pitch from quizList
			triad = rand.nextInt((quizList.size() * 100) + 1) % quizList.size();
			root = rand.nextInt(1201) % 12;
			
			// Gets pitch that will be played and sets it to answer
			answer = quizList.get(triad);
			
			// Play pitch in answer on standardized pattern
			play(root, answer, player);
			
			System.out.print("What triad was just played? ");
			response = scanner.next();
			
			while(!response.equals(answer) && !response.equals("Skip")) {
				System.out.print("That was not the correct triad. Try again or type \"Skip\" to skip: ");
				
				response = scanner.next();
			}
			
			System.out.println("Type \"Ready\" to play a triad. Type \"Done\" to return to the main menu.");
			response = scanner.next();
		}
	}
	
	public void act(Scanner scanner, Player player) {
		quizList = new ArrayList<String>();
		
		// Welcome
		System.out.println("Welcome to the Triad Training mode.\n");
		
		// Instructions for user to input valid notes
		System.out.println("Please type in triads you would like to be quizzed on one at a time.");
		System.out.println("To finish putting in triads, type \"Done\".");
		System.out.println("Press Q to quit and return to the main menu.");
		System.out.println("If you would like to be quizzed on all triads, type \"All\".\n");
		
		System.out.println("Triads should be one of the following: d, m, M, or A\n");
		
		System.out.print("Enter triad: ");
		String triad = scanner.next();
		
		while(!(triad.equals("Done")) && !(triad.equals("Q"))) {
			// If "All", then include all triad and move onto quizing
			if(triad.equals("All")) {
				quizList.clear();
				quizList.add("d");
				quizList.add("m");
				quizList.add("M");
				quizList.add("A");
				
				break;
			}
			
			// Check if the given triad is valid, then add it to the list if it is not already there
			if(triad.equals("d") || triad.equals("m") || triad.equals("M") || triad.equals("A")) {
				if(!quizList.contains(triad)) {
					quizList.add(triad);
				} else {
					System.out.println("This triad is already in the list.");
				}
			} else {
				System.out.println("This is not a valid triad.");
				System.out.println("The list of valid triad are: d, m, M, or A\n");
			}
			
			// Keep loop going
			System.out.print("Enter triad: ");
			triad = scanner.next();
		}
		
		// Start quizzing if the last response given when looking for pitches was not Q
		if(!(triad.equals("Q"))) {
			System.out.print("\nYour will be quizzed on the following: ");
			for(int i = 0; i < quizList.size(); i++) {
				System.out.print(quizList.get(i) + " ");
			}
			System.out.println("\n");
			
			startQuizing(scanner, player);
		}
	}
}