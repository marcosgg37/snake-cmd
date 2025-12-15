package main.game.cells;

import java.awt.Point;

public abstract class Cell {

	private Point coords;
	protected char value;
	
	public Cell(Point p) {
		this.coords = p;
		this.value = ' ';
	}
	
	// GETTERS
	public Point getCoords() {
		return this.coords;
	}
	
	public char getValue() {
		return this.value;
	}
	
	public void setCoords(int x, int y) {
		this.coords = new Point(x, y);
	}
	
	public void setCoords(Point p) {
		this.coords = p;
	}
	
}
