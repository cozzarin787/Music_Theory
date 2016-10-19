import java.util.Scanner;
import org.jfugue.player.Player;

public class main{
	public static void printmenu() {
		System.out.println("Menu:");
		System.out.println("1: Interval Training");
		System.out.println("2: Pitch Training");
		System.out.println("3: Triad Training");
		System.out.println("4: Inversion Training");
		System.out.println("5: Rhythm Training");
		System.out.println("PUT MODES HERE");
		System.out.println("Q: Quit");
		System.out.print("Select one of the choices above to begin: ");
	}
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Player player = new Player();
		
		Intervals intervalTrainer = new Intervals();
		Pitches pitchTrainer = new Pitches();
		Triads triadTrainer = new Triads();
		Inversions inversionTrainer = new Inversions();
		Rhythms rhythmTrainer = new Rhythms();
		
		System.out.println("Welcome to the Music Theory Study Buddy.\n");
		printmenu();
		
		String res = scanner.next();
		
		while(!(res.equals("Q"))){
			
			if(res.equals("1")) {
				intervalTrainer.act(scanner, player);
			} else if(res.equals("2")) {
				pitchTrainer.act(scanner, player);
			} else if(res.equals("3")) {
				triadTrainer.act(scanner, player);
			} else if(res.equals("4")) {
				inversionTrainer.act(scanner, player);
			} else if(res.equals("5")) {
				rhythmTrainer.act(scanner, player);
			}
			
			System.out.println("\n\nWelcome back to the main menu.\n");
			printmenu();
			
		 	res = scanner.next();
		}
		
		System.exit(0);
	}
}