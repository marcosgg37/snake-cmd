package main.input;

import main.menu.Menu;

public class MenuInputManager implements IInputManager {

	private Menu menu;
	
	public MenuInputManager(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void parseKeyUp() {
		menu.moveCursorUp();
		menu.draw();
	}

	@Override
	public void parseKeyDown() {
		menu.moveCursorDown();
		menu.draw();
	}

	@Override
	public void parseKeyLeft() {}

	@Override
	public void parseKeyRight() {
		menu.confirmOption();
	}

}
