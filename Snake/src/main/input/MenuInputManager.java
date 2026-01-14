package main.input;

import main.menu.Menu;
import main.scenes.MenuScene;

public class MenuInputManager implements IInputManager {

	private Menu menu;
	private MenuScene ms;
	
	public MenuInputManager(Menu menu, MenuScene ms) {
		this.menu = menu;
		this.ms = ms;
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
