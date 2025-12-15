package main.game.cells;

import java.awt.Point;

public interface ICell {

	// GETTERS
	Point getCoords();

	char getValue();

	void setCoords(int x, int y);

	void setCoords(Point p);

}