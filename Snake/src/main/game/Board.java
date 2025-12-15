package main.game;

import java.awt.Point;

import main.game.cells.Cell;

public class Board {
	// CONSTANTS
	public static final int DEFAULT_BOARD_SIZE = 10;
	
	
	// SINGLETON
	private static Board instance;
	
	private Board() {
		this.board = new Cell[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
	}
	
	public static Board getInstance() {
		if (instance == null) 
			instance = new Board();
		return instance;
	}
	
	
	// ATTRIBUTES & METHODS
	private Cell[][] board;
	
	public void setSize(int size) {
		if (size <= 2) throw new IllegalArgumentException("!!! BOARD CREATION - Invalid size [" + size + "]");
		
		this.board = new Cell[size][size];
	}
	
	public char getCharAt(Point p) {
		return this.board[p.x][p.y].getValue();
	}
	
}
