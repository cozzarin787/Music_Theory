import java.util.Scanner;
import org.jfugue.player.Player;
import java.util.ArrayList;
import java.util.Random;

public class Inversions extends Trainer{
	private Random rand;
	private String[] chromatic;
	private ArrayList<String> quizList;
	
	public Inversions() {
		super();
		rand = new Random();
		chromatic = super.chromatic;
	}
	
	public void play(int root, String inversion, Player player) {
		System.out.println(root);
		String rootPitch = chromatic[root];
		String thirdPitch = super.getIntervalPitch(root, 4);
		String fifthPitch = super.getIntervalPitch(root, 7);
		String pattern = "";
		
		if(root + 7 < 12) {
			fifthPitch += "5";
		} else {
			fifthPitch += "6";
		}
		
		switch (inversion) {
			case "root":
				rootPitch += "5";
				if(root + 4 < 12) {
					thirdPitch += "5";
				} else {
					thirdPitch += "6";
				}
				pattern = rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w " + rootPitch + "h " + thirdPitch + "h " + fifthPitch + "h " + rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w";
				break;
			case "first":	
				rootPitch += "6";
				if(root + 4 < 12) {
					thirdPitch += "5";
				} else {
					thirdPitch += "6";
				}
				pattern = rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w " + thirdPitch + "h " + fifthPitch + "h " + rootPitch + "h " + rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w";
				break;
			case "second":
				rootPitch += "6";
				if(root + 4 < 12) {
					thirdPitch += "6";
				} else {
					thirdPitch += "7";
				}
				pattern = rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w " + fifthPitch + "h " + rootPitch + "h " + thirdPitch + "h " + rootPitch + "w+" + thirdPitch + "w+" + fifthPitch + "w";
			default:
				// Should not happen
				break;
		}
		
		System.out.println(pattern);
		
		player.play(pattern);
	}
	
	public void startQuizing(Scanner scanner, Player player) {
		System.out.println("Type \"Ready\" to play an inversion. Type \"Done\" to return to the main menu.");

		String response = scanner.next(), answer = "";
		int inversion, root;
		
		while(!(response.equals("Done"))) {
			// Random pitch from quizList
			inversion = rand.nextInt((quizList.size() * 100) + 1) % quizList.size();
			root = rand.nextInt(1201) % 12;
			
			// Gets pitch that will be played and sets it to answer
			answer = quizList.get(inversion);
			
			// Play pitch in answer on standardized pattern
			play(root, answer, player);
			
			System.out.print("What inversion was just played? ");
			response = scanner.next();
			
			while(!response.equals(answer) && !response.equals("Skip")) {
				System.out.print("That was not the correct inversion. Try again or type \"Skip\" to skip: ");
				
				response = scanner.next();
			}
			
			System.out.println("Type \"Ready\" to play a inversion. Type \"Done\" to return to the main menu.");
			response = scanner.next();
		}
	}
	
	public void act(Scanner scanner, Player player) {
		quizList = new ArrayList<String>();
		
		// Welcome
		System.out.println("Welcome to the Inversion Training mode.\n");
		
		// Instructions for user to input valid notes
		System.out.println("Please type in inversions you would like to be quizzed on one at a time.");
		System.out.println("To finish putting in inversions, type \"Done\".");
		System.out.println("Press Q to quit and return to the main menu.");
		System.out.println("If you would like to be quizzed on all inversions, type \"All\".\n");
		
		System.out.println("Inversions should be root, first, or second\n");
		
		System.out.print("Enter inversion: ");
		String inversion = scanner.next();
		
		while(!(inversion.equals("Done")) && !(inversion.equals("Q"))) {
			// If "All", then include all triad and move onto quizing
			if(inversion.equals("All")) {
				quizList.clear();
				quizList.add("root");
				quizList.add("first");
				quizList.add("second");
				
				break;
			}
			
			// Check if the given triad is valid, then add it to the list if it is not already there
			if(inversion.equals("root") || inversion.equals("first") || inversion.equals("second")) {
				if(!quizList.contains(inversion)) {
					quizList.add(inversion);
				} else {
					System.out.println("This inversion is already in the list.");
				}
			} else {
				System.out.println("This is not a valid inversion.");
				System.out.println("The list of valid inversion are: root, first, or second\n");
			}
			
			// Keep loop going
			System.out.print("Enter inversion: ");
			inversion = scanner.next();
		}
		
		// Start quizzing if the last response given when looking for pitches was not Q
		if(!(inversion.equals("Q"))) {
			System.out.print("\nYour will be quizzed on the following: ");
			for(int i = 0; i < quizList.size(); i++) {
				System.out.print(quizList.get(i) + " ");
			}
			System.out.println("\n");
			
			startQuizing(scanner, player);
		}
	}
}