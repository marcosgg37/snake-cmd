package main.game;

import static java.util.Map.entry;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Map;

import main.game.cells.FoodCell;
import main.game.cells.SnakeCell;
import main.input.GameInputManager;

public class Game {
	
	// TODO: refactorizar para que el output esté en su propia clase
	private Map<String, String> colors = Map.ofEntries(
			entry("red","\u001B[31m"),
			entry("blue", "\u001B[34m"),
			entry("green", "\u001B[32m"),
			entry("yellow", "\u001B[33m"),
			entry("white", "\u001B[37m"),
			entry("reset", "\u001B[0m"),
			entry("", "")
		);
	
	private Map<String, String> bgColors = Map.ofEntries(
			entry("red","\u001B[41m"),
			entry("blue", "\u001B[44m"),
			entry("green", "\u001B[42m"),
			entry("yellow", "\u001B[43m"),
			entry("white", "\u001B[47m"),
			entry("reset", "\u001B[0m"),
			entry("", "")
		);
	
	/**
	 * The default delay between game updates 
	 * 
	 * Default half a second (0.5)
	 */
	public static final int DEFAULT_TICK_DELAY = 300;
	
	public static final int DEFAULT_BOARD_SIZE = 10;
	public static final int INITIAL_SNAKE_SIZE = 3;
	public static final Point DEFAULT_INITIAL_POSITION = new Point(0, 0);
	
	private GameInputManager im;
	
	private Runnable runGame = new Runnable() {
		public void run() {
			do {
				updateBoard();
				showBoard();
				try {
					Thread.sleep(deltaTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updateSnakePosition();
				if (checkFoodEaten()) {
					addSnakeCell();
					spawnFood();
					checkWin();
				}
				if (checkCollision()) {
					gameOver();
				}
			} while(!gameOver);
		}
	};
	
	private int deltaTime;
	
	private char[][] cellBoard;
	private String[][] colorBoard;
	private LinkedList<SnakeCell> snake = new LinkedList<>();
	private FoodCell food;
	
	private Point lastTailPosition;
	
	private boolean gameOver = false;
	
	public Game(GameInputManager im, int deltaTime) {
		this.im = im;
		
		initialize();
		
		this.deltaTime = deltaTime;
	}
	
	public Game(GameInputManager im) {
		this(im, DEFAULT_TICK_DELAY);
	}
	
	public void initialize() {
		createBoard();
		initializeSnake();
		im.setDirection('d');
		spawnFood();
	}
	
	private void createBoard() {
		this.cellBoard = new char[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
		this.colorBoard = new String[cellBoard.length][cellBoard.length];
	}
	
	private void initializeSnake() {
		for (int i = 0; i < INITIAL_SNAKE_SIZE; i++) {
			addSnakeCell();
		}
		this.lastTailPosition = snake.getFirst().getCoords();
	}
	
	private void addSnakeCell() {
		if (snake.isEmpty()) {
			SnakeCell head = new SnakeCell(DEFAULT_INITIAL_POSITION, true, true);
			snake.add(head);
			this.lastTailPosition = head.getCoords().getLocation(); 
			return;
		}
		Point newCellCoords = this.lastTailPosition.getLocation();
		
		if (snake.getFirst().getCoords().equals(newCellCoords))
			newCellCoords.setLocation(newCellCoords.x, newCellCoords.y + 1);
		
		snake.getFirst().isNoLongerTail();
		snake.addFirst(new SnakeCell(newCellCoords, false, true));
	}
	
	private void showSnakeInBoard() {
		for (SnakeCell cell : snake) {
			cellBoard[cell.getCoords().y][cell.getCoords().x] = cell.getValue();
			colorBoard[cell.getCoords().y][cell.getCoords().x] = cell.getColor();
		}
	}
	
	private void updateSnakePosition() {
		this.lastTailPosition = snake.getFirst().getCoords().getLocation();
		
		SnakeCell lastCell = null;
		for (SnakeCell cell : snake) {
			if (cell.isTail()) {
				lastCell = cell;
				continue;
			}
			
			lastCell.setCoords(cell.getCoords());
			lastCell = cell;
			
			if (cell.isHead()) {
				Point next = getNextPoint(im.getDirection());
				if (next != null) cell.setCoords(next);
				else {
					gameOver();
					return;
				}
			}
			
		}
	}
	
	private boolean checkCollision() {
		boolean collision = false;
		for (SnakeCell cell : snake) {
			if (cell.isHead() || cell.isTail()) continue;
			if (cell.getCoords().equals(snake.getLast().getCoords())) {
				collision = true;
				break;
			}
		}
		return collision;
	}
	
	private Point getNextPoint(char direction) {
		SnakeCell head = snake.getLast();
		
		switch (direction) {
		case 'w':
			if (snake.getLast().getCoords().y - 1 >= 0) return new Point(head.getCoords().x, head.getCoords().y - 1);
			break;
		case 's':
			if (snake.getLast().getCoords().y + 1 < cellBoard.length) return new Point(head.getCoords().x, head.getCoords().y + 1);
			break;
		case 'a':
			if (snake.getLast().getCoords().x - 1 >= 0) return new Point(head.getCoords().x - 1, head.getCoords().y);
			break;
		case 'd':
			if (snake.getLast().getCoords().x + 1 < cellBoard.length) return new Point(head.getCoords().x + 1, head.getCoords().y);
			break;
		}
		return null;
	}
	
	private void spawnFood() {
		if (this.food == null) {
			this.food = new FoodCell(new Point(0, 0));
		}
		this.food.setCoords(
				new Point(
						(int) (Math.random() * cellBoard.length), 
						(int) (Math.random() * cellBoard.length)
				)
		);
		
	}
	
	private void showFoodInBoard() {
		cellBoard[this.food.getCoords().y][this.food.getCoords().x] = food.getValue();
		colorBoard[this.food.getCoords().y][this.food.getCoords().x] = food.getColor();
	}
	
	private boolean checkFoodEaten() {
		return snake.getLast().getCoords().equals(food.getCoords());
	}
	
	private void updateBoard() {
		for (int i = 0; i < cellBoard.length; i++)
			for (int j = 0; j < cellBoard.length; j++) {
				cellBoard[i][j] = ' ';
				colorBoard[i][j] = "";
			}
		showFoodInBoard();
		showSnakeInBoard();
	}
	
	public void showBoard() {
		
		System.out.print("\033[H\033[2J");
		
		for (int i = 0; i < (cellBoard.length * 2) + 4; i++) System.out.print(bgColors.get("white") + "=" + bgColors.get("reset"));
		
		System.out.println();
		for (int i = 0; i < cellBoard.length; i++) {
			System.out.print(bgColors.get("white") + colors.get("white") + "| " + bgColors.get("reset"));
			for (int j = 0; j < cellBoard.length; j++)
				System.out.print(bgColors.get(colorBoard[i][j]) + colors.get(colorBoard[i][j]) + cellBoard[i][j] + " " + bgColors.get("reset"));
			System.out.print(bgColors.get("white") + colors.get("white") + "| " + bgColors.get("reset"));
			System.out.println();
		}
		
		for (int i = 0; i < (cellBoard.length * 2) + 4; i++) System.out.print(bgColors.get("white") + colors.get("white") + "=" + bgColors.get("reset"));
		System.out.println();
	}
	
	private void gameOver() {
		this.gameOver = true;
		System.out.println("--- Fin del juego ---");
	}
	
	private void checkWin() {
		if (snake.size() >= cellBoard.length * cellBoard.length) {
			System.out.println("*** VICTORIA! ***");
			gameOver();
		}
	}
	
	public Runnable getRunnable() {
		return this.runGame;
	}
	
}
