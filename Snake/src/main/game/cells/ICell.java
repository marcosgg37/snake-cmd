package main.game.cells;

import java.awt.Point;

public interface ICell {

	// GETTERS
	Point getCoords();

	char getValue();
	String getColor();

	void setCoords(int x, int y);
	void setCoords(Point p);

}