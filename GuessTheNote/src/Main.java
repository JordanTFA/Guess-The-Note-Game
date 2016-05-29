import java.io.File;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {

	private static Scanner setupScan;
	private static Scanner playScan;
	private static Scanner mainScan;

	public static void main(String[] args) {
		
		mainScan = new Scanner(System.in);

		System.out.println("Welcome to the 'Guess a note' game!");

		int MAX = setup();
		char answer = play(MAX);
		
		while(answer != 'n'){
			if(answer == 'y')
				answer = play(MAX);
			
			else if (answer == 'd')
				{
					MAX = setup();
					System.out.println("Would you like to play again? (y/n)");
					answer = mainScan.next(".").charAt(0);
				}
		}
		
		System.out.println("Exiting...");
		System.exit(0);
		
	}
	
	public static int setup(){


		System.out.println("Type 'e' for easy mode and 'h' for hard mode");
		
		setupScan = new Scanner(System.in);
		
		char difficulty = setupScan.next(".").charAt(0);
		
		while(difficulty != 'h' && difficulty != 'e')
		{
			difficulty = setupScan.next(".").charAt(0);
		}
		
		final int MAX;
		
		if(difficulty == 'e')
		{
			MAX = 7;
		}
		else{
			MAX = 12;
		}
		
		return MAX;
	}
	
	public static char play(int MAX){
		
		Random Generator = new Random();
		playScan = new Scanner(System.in);

		String note = null;
		int inote;
		
		int lives = 3;
		boolean correct = false;
		
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
			case 7: note = "Bb";
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
			if(guess.equals(note))
			{
				System.out.println("Congratulations! You were correct!");
				correct = true;
			}else{
				lives--;
				System.out.println("Sorry, you were wrong! You now have " + lives + " lives remaining.");

			}
		}
		
		System.out.println("Done\nThe note was " + note + "\nWould you like to play again? (y/n) or 'd' to change difficulty");
		
		char answer = playScan.next(".").charAt(0);
		
		while(answer != 'y' && answer != 'n' && answer != 'd')
		{
			answer = setupScan.next(".").charAt(0);
		}
		for (int i = 0; i < 150; ++i) System.out.println();
		
		return answer;

	}

}
