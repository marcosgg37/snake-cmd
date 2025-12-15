package main.game;

import java.awt.Point;
import java.util.LinkedList;

import main.game.cells.FoodCell;
import main.game.cells.SnakeCell;
import main.input.InputManager;

public class Game {
	
	public static final int DEFAULT_DELTA_TIME = 500;
	
	public static final int DEFAULT_BOARD_SIZE = 10;
	public static final int INITIAL_SNAKE_SIZE = 3;
	public static final Point DEFAULT_INITIAL_POSITION = new Point(0, 0);
	
	private InputManager im;
	
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
	
	private char[][] board;
	private LinkedList<SnakeCell> snake = new LinkedList<>();
	private FoodCell food;
	
	private Point lastTailPosition;
	
	private boolean gameOver = false;
	
	public Game(InputManager im, int deltaTime) {
		this.im = im;
		
		createBoard();
		initializeSnake();
		spawnFood();
		
		this.deltaTime = deltaTime;
	}
	
	public Game(InputManager im) {
		this(im, DEFAULT_DELTA_TIME);
	}
	
	private void createBoard() {
		this.board = new char[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
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
			board[cell.getCoords().y][cell.getCoords().x] = cell.getValue();
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
			if (snake.getLast().getCoords().y + 1 < board.length) return new Point(head.getCoords().x, head.getCoords().y + 1);
			break;
		case 'a':
			if (snake.getLast().getCoords().x - 1 >= 0) return new Point(head.getCoords().x - 1, head.getCoords().y);
			break;
		case 'd':
			if (snake.getLast().getCoords().x + 1 < board.length) return new Point(head.getCoords().x + 1, head.getCoords().y);
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
						(int) (Math.random() * board.length), 
						(int) (Math.random() * board.length)
				)
		);
		
	}
	
	private void showFoodInBoard() {
		board[this.food.getCoords().y][this.food.getCoords().x] = food.getValue();
	}
	
	private boolean checkFoodEaten() {
		return snake.getLast().getCoords().equals(food.getCoords());
	}
	
	private void updateBoard() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				board[i][j] = ' ';
		showFoodInBoard();
		showSnakeInBoard();
	}
	
	public void showBoard() {
		
		for (int i = 0; i < (board.length * 2) + 3; i++) System.out.print("=");
		
		System.out.println();
		for (char[] row : board) {
			System.out.print("| ");
			for (char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.print("|");
			System.out.println();
		}
		
		for (int i = 0; i < (board.length * 2) + 3; i++) System.out.print("=");
		System.out.println();
	}
	
	private void gameOver() {
		this.gameOver = true;
		System.out.println("--- Fin del juego ---");
	}
	
	private void checkWin() {
		if (snake.size() >= board.length * board.length) {
			System.out.println("*** YOU WIN! ***");
			gameOver();
		}
	}
	
	public Runnable getRunnable() {
		return this.runGame;
	}
	
}
