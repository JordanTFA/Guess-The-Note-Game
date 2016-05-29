import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
	
	
	// Scanners
	private static Scanner setupScan;
	private static Scanner playScan;
	private static Scanner mainScan;

	// The main, but of course
	public static void main(String[] args) {
		
		// Main scanner
		mainScan = new Scanner(System.in);

		System.out.println("Welcome to the 'Guess a note' game!");

		
		int MAX = setup();			// Get the difficulty and assign the max value
		char answer = play(MAX);	// Actually playing the game
		
		while(answer != 'n'){
			if(answer == 'y')		// Play again
				answer = play(MAX);	
			
			else if (answer == 'd') // Change difficulty
				{
					MAX = setup();
					System.out.println("Would you like to play again? (y/n)");
					answer = mainScan.next(".").charAt(0);
				}
		}
		
		// Closing the program
		System.out.println("Exiting...");
		System.exit(0);
		
	}
	
	// Getting the difficulty level from the user
	// and using it to assign which notes will be used
	public static int setup(){

		System.out.println("Type 'e' for easy mode and 'h' for hard mode");
		
		// Scanner
		setupScan = new Scanner(System.in);
		
		char difficulty = setupScan.next(".").charAt(0);
		
		// Input validation
		while(difficulty != 'h' && difficulty != 'e')
		{
			difficulty = setupScan.next(".").charAt(0);
		}
		
		// Max value of which note will be picked
		int MAX;
		
		if(difficulty == 'e')
		{
			MAX = 7;	// Easy
		}
		else{
			MAX = 12;	// Hard
		}
		
		return MAX;
	}
	
	// Actually playing the game
	public static char play(int MAX){
		
		Random Generator = new Random();		// Random generator for the note
		playScan = new Scanner(System.in);		// Scanner

		String note = null;	// Note
		int inote;	// Better variable name pending
		
		int lives = 3;	// Lives system, this can be changed in the future. TODO: Make this more variable.
		boolean correct = false;
		
		// Generate a random number
		inote = Generator.nextInt(MAX);
		
		switch(inote)
		{
			case 0: note = "A";
				break;
			case 1: note = "B";
				break;
			case 2: note = "C";
				break;
			case 3: note = "D";
				break;
			case 4: note = "E";
				break;
			case 5: note = "F";
				break;
			case 6: note = "G";
				break;
			case 7: note = "Bb";	// Hard notes start here
				break;
			case 8: note = "C#";
				break;
			case 9: note = "Eb";
				break;
			case 10: note = "F#";
				break;
			case 11: note = "G#";
				break;
		}
		
	    try
	    {
	    	// Plays the sound
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File("src\\notes\\" + note + ".wav")));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
			
		String guess = null;
		
		while(correct ^ lives != 0)
		{
			guess = playScan.nextLine();
			if(guess.equals(note))		// If correct
			{
				System.out.println("Congratulations! You were correct!");
				correct = true;
			}else{						// If wrong
				lives--;				// A life is lost
				System.out.println("Sorry, you were wrong! You now have " + lives + " lives remaining.");

			}
		}
		
		System.out.println("Done\nThe note was " + note + "\nWould you like to play again? (y/n) or 'd' to change difficulty");
		
		// Get user's answer if they want to play again
		char answer = playScan.next(".").charAt(0);
		
		while(answer != 'y' && answer != 'n' && answer != 'd')
		{
			answer = setupScan.next(".").charAt(0);
		}
		for (int i = 0; i < 150; ++i) System.out.println();	// This is a cheeky way of clearing the console, there'll be a better way.
		
		return answer;

	}

}
