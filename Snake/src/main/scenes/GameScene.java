package main.scenes;

import main.game.Game;
import main.input.GameInputManager;
import main.input.IInputManager;
import main.input.KeyAdapter;

public class GameScene implements IScene {

	private SceneManager sm;

	private Game game;
	private GameInputManager im;
	private KeyAdapter ka;

	public GameScene(SceneManager sm) {
		this.sm = sm;
		this.im = new GameInputManager();
		this.ka = this.sm.getKeyAdapter();
	}

	@Override
	public void loadScene() {
		this.ka.setInputManager(im);
		this.game = new Game(im);
		this.run();
	}

	@Override
	public void unloadScene() {
		game.initialize();
	}

	@Override
	public String getName() {
		return "Game Scene";
	}

	@Override
	public void run() {
		this.game.getRunnable().run();
		this.stop();
	}

	@Override
	public IInputManager getInputManager() {
		return this.im;
	}

	public void stop() {
		sm.swapToScene(sm.getMenu());
	}

}
