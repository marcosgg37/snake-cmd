package main.input;

// import java.util.Scanner;

public class GameInputManager implements IInputManager {

	public static final char LEFT_DIRECTION = 'a', RIGHT_DIRECTION = 'd', UP_DIRECTION = 'w', DOWN_DIRECTION = 's';
	private static final char DEFAULT_STARTING_DIRECTION = RIGHT_DIRECTION;
	
	private char direction;
		
	public GameInputManager() {
		this.direction = DEFAULT_STARTING_DIRECTION;
	}
	
	@Override
	public void parseKeyUp() {
		this.setDirection('w');
	}

	@Override
	public void parseKeyDown() {
		this.setDirection('s');
	}

	@Override
	public void parseKeyLeft() {
		this.setDirection('a');
	}

	@Override
	public void parseKeyRight() {
		this.setDirection('d');
	}
	
	public void setDirection(char dir) {
		if (!"wasd".contains(dir + "")) return;
		
		this.direction = dir;
	}
	
	public char getDirection() {
		return this.direction;
	}
}
