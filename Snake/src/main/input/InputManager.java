package main.input;

// import java.util.Scanner;

public class InputManager {
	
	public static final char LEFT_DIRECTION = 'a', RIGHT_DIRECTION = 'd', UP_DIRECTION = 'w', DOWN_DIRECTION = 's';
	private static final char DEFAULT_STARTING_DIRECTION = RIGHT_DIRECTION;
	
	/*
	private Runnable runInputManager = new Runnable() {
		public void run() {
			do {
				waitForDirection();
			} while(true);
		}
	};
	*/
	
	//private Scanner in;
	private char direction;
		
	public InputManager() {
		//this.in = new Scanner(System.in);
		this.direction = DEFAULT_STARTING_DIRECTION;
	}
	
	/*
	public void waitForDirection() {
		String input = in.nextLine();
		if (input.isBlank())
			input = String.valueOf(direction);
		this.direction = input.charAt(0);
		
	}
	*/
	
	public void setDirection(char dir) {
		if (!"wasd".contains(dir + "")) return;
		
		this.direction = dir;
	}
	
	public char getDirection() {
		return this.direction;
	}
	
	/*
	public Runnable getRunnable() {
		return this.runInputManager;
	}
	*/
}
