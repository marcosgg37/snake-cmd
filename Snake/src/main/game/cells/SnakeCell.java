package main.game.cells;

import java.awt.Point;

public class SnakeCell extends Cell {

	private boolean isHead;
	private boolean isTail;
	
	public SnakeCell(Point p, boolean isHead, boolean isTail) {
		super(p);
		this.isHead = isHead;
		this.isTail = isTail;
		super.value = isHead ? '@' : '█';
		super.color = "green";
	}

	public void isNoLongerTail() {
		this.isTail = false;
	}
	
	public boolean isHead() {
		return this.isHead;
	}
	
	public boolean isTail() {
		return this.isTail;
	}
	
}
