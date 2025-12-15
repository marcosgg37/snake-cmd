package main.game.cells;

import java.awt.Point;

public abstract class BaseCell implements Cell {

	private Point coords;
	protected char value;
	
	public BaseCell(Point p) {
		this.coords = p;
	}
	
	// GETTERS
	@Override
	public Point getCoords() {
		return this.coords;
	}
	
	@Override
	public char getValue() {
		return this.value;
	}
	
	@Override
	public void setCoords(int x, int y) {
		this.coords = new Point(x, y);
	}
	
	@Override
	public void setCoords(Point p) {
		this.coords = p;
	}
	
}
