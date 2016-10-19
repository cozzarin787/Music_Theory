import java.util.Scanner;
import org.jfugue.player.Player;
import java.util.ArrayList;
import java.util.Random;

public class Pitches extends Trainer{
	private Random rand;
	private String[] chromatic;
	private ArrayList<String> quizList;
	
	public Pitches() {
		super();
		rand = new Random();
		chromatic = super.chromatic;
	}
	
	public String convert(String answer) {
		switch (answer) {
			case "Db":
				return "C#";
			case "Eb":
				return "D#";
			case "Gb":
				return "F#";
			case "Ab":
				return "G#";
			case "Bb":
				return "A#";
			default:
				// Natural notes
				return answer;
		}
	}
	
	public void startQuizing(Scanner scanner, Player player) {
		System.out.println("Type \"Ready\" to play a pitch. Type \"Done\" to return to the main menu.");

		String response = scanner.next(), answer = "";
		int pitch;
		
		while(!(response.equals("Done"))) {
			// Random pitch from quizList
			pitch = rand.nextInt((quizList.size() * 100) + 1) % quizList.size();
			
			// Gets pitch that will be played and sets it to answer
			answer = quizList.get(pitch);
			
			// Play pitch in answer on standardized pattern
			player.play(answer + "5h " + answer + "4h " + answer + "6h " + answer + "3h " + answer + "5h");
			
			System.out.print("What pitch was just played? ");
			response = convert(scanner.next());
			
			while(!response.equals(answer) && !response.equals("Skip")) {
				System.out.print("That was not the correct pitch. Try again or type \"Skip\" to skip: ");
				
				response = convert(scanner.next());
			}
			
			System.out.println("Type \"Ready\" to play a pitch. Type \"Done\" to return to the main menu.");
			response = scanner.next();
		}
	}
	
	public void printPitches() {
		for(int i = 0; i < chromatic.length; i++) {
			System.out.print(chromatic[i] + " ");
		}
		System.out.println("\nNote: All sharps are regarded as the same as their enharmonic equivalent pitches.\nC# is the same as Db\n");
	}
	
	public boolean validPitch(String pitch) {
		for(int i = 0; i < chromatic.length; i++) {
			if(chromatic[i].equals(pitch)) {
				return true;
			}
		}
		return false;
	}
	
	public void act(Scanner scanner, Player player) {
		quizList = new ArrayList<String>();
		
		// Welcome
		System.out.println("Welcome to the Pitch Training mode.\n");
		
		// Instructions for user to input valid notes
		System.out.println("Please type in pitches you would like to be quizzed on one at a time.");
		System.out.println("To finish putting in pitches, type \"Done\".");
		System.out.println("Press Q to quit and return to the main menu.");
		System.out.println("If you would like to be quizzed on all pitches, type \"All\".\n");
		
		System.out.println("Pitches should be ");
		printPitches();
		
		System.out.print("Enter pitch: ");
		String pitch = scanner.next();
		
		while(!(pitch.equals("Done")) && !(pitch.equals("Q"))) {
			// If "All", then include all pitches and move onto quizing
			if(pitch.equals("All")) {
				quizList.clear();
				quizList.add("C");
				quizList.add("C#");
				quizList.add("D");
				quizList.add("D#");
				quizList.add("E");
				quizList.add("F");
				quizList.add("F#");
				quizList.add("G");
				quizList.add("G#");
				quizList.add("A");
				quizList.add("A#");
				quizList.add("B");
				
				break;
			}
			
			// Check if the given pitch is valid, then add it to the list if it is not already there
			if(validPitch(pitch)) {
				if(!quizList.contains(pitch)) {
					quizList.add(pitch);
				} else {
					System.out.println("This pitch is already in the list.");
				}
			} else {
				System.out.println("This is not a valid pitch.");
				System.out.print("The list of valid pitches are: ");
				printPitches();
			}
			
			// Keep loop going
			System.out.print("Enter pitch: ");
			pitch = scanner.next();
		}
		
		// Start quizzing if the last response given when looking for pitches was not Q
		if(!(pitch.equals("Q"))) {
			System.out.print("\nYour will be quizzed on the following: ");
			for(int i = 0; i < quizList.size(); i++) {
				System.out.print(quizList.get(i) + " ");
			}
			System.out.println("\n");
			
			startQuizing(scanner, player);
		}
	}
}