import java.util.Scanner;
import org.jfugue.player.Player;
import java.util.ArrayList;
import java.util.Random;
import java.util.InputMismatchException;

public class Rhythms {
	private Random rand;
	private int beats, measures;
	
	public Rhythms() {
		rand = new Random();
	}
	
	public String playRandomRhtyhm(Player player) {
		
		
		player.play(rhythm);
	}
	
	public void startQuizing(Scanner scanner, Player player) {
		System.out.println("Type \"Ready\" to play a rhythm. Type \"Done\" to return to the main menu.");
		
		String response = scanner.next();
		
		while(!response.equals("Done")) {
			String answer = playRandomRhythm(player);
		}
	}
	
	public void act(Scanner scanner, Player player) {		
		// Welcome
		System.out.println("Welcome to the Pitch Training mode.\n");
		System.out.println("For the sake of simplicity, rhythms can only be up to 4 measures long with up to 4 beats per measure.");
		System.out.println("\nNotate all rhythms as the following:");
		System.out.println("Whole notes: w");
		System.out.println("Half notes: h");
		System.out.println("Quarter notes: q");
		System.out.println("Eighth notes: i");
		System.out.println("Sixteenth notes: s");
		beats = -1;
		measures = -1;
		int response;
		
		while(beats == -1) {
			System.out.print("How many beats would you like per measure? ");
			
			try{
				response = scanner.nextInt();
				if(response < 5 && response > 0) {
					beats = response;
				} else {
					System.out.println("That was not a number between 1 and 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("That was not a number. Try again.");
				scanner.next();
			}
		}
		
		while(measures == -1) {
			System.out.print("How many measures would you like per rhythm? ");
			
			try{
				response = scanner.nextInt();
				if(response < 5 && response > 0) {
					measures = response;
				} else {
					System.out.println("That was not a number between 1 and 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("That was not a number. Try again.");
				scanner.next();
			}
		}
		
		startQuizing(scanner, player);
	}
}