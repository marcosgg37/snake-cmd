package main.scenes;

import java.util.ArrayList;

import main.input.IInputManager;
import main.input.KeyAdapter;
import main.input.MenuInputManager;
import main.menu.Menu;

public class MenuScene implements IScene {

	private SceneManager sm;

	private MenuInputManager im;
	private KeyAdapter ka;

	private ArrayList<String> options;
	private Menu menu;

	public MenuScene(SceneManager sm) {
		this.sm = sm;

		this.options = new ArrayList<>();
		this.options.add("Jugar");
		this.options.add("Salir");
		
		this.menu = new Menu(options.toArray(new String[0]));
		this.im = new MenuInputManager(menu);
		this.ka = this.sm.getKeyAdapter();
	}

	@Override
	public void loadScene() {
		this.ka.setInputManager(im);
		this.run();
	}

	@Override
	public void unloadScene() {
		menu.reset();
	};

	@Override
	public String getName() {
		return "Menu Scene";
	}

	@Override
	public void run() {
		menu.draw();
		while (true)
			try {
				Thread.sleep(10);
				if (menu.isOptionConfirmed())
					break;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		switch (menu.getSelectedOption()) {
		case 0:
			startGame();
			break;
		case 1:
		default:
			System.exit(0);
			break;
		}
	}

	@Override
	public IInputManager getInputManager() {
		return this.im;
	}

	public void startGame() {
		sm.swapToScene(sm.getGame());
	}

}
