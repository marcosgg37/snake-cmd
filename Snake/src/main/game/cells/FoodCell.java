package main.game.cells;

import java.awt.Point;

public class FoodCell extends Cell {

	public FoodCell(Point p) {
		super(p);
		super.value = '+';
		super.color = "red";
	}

}
