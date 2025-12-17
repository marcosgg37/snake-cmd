package main.input;

// import java.util.Scanner;

public class InputManager {
	
	public static final char LEFT_DIRECTION = 'a', RIGHT_DIRECTION = 'd', UP_DIRECTION = 'w', DOWN_DIRECTION = 's';
	private static final char DEFAULT_STARTING_DIRECTION = RIGHT_DIRECTION;
	
	private char direction;
		
	public InputManager() {
		this.direction = DEFAULT_STARTING_DIRECTION;
	}
	
	public void setDirection(char dir) {
		if (!"wasd".contains(dir + "")) return;
		
		this.direction = dir;
	}
	
	public char getDirection() {
		return this.direction;
	}
}
