package main.scenes;

import com.github.kwhat.jnativehook.GlobalScreen;

import main.input.KeyAdapter;

public class SceneManager {

	private MenuScene menu;
	private GameScene game;
	private KeyAdapter ka;
	
	private IScene currentScene;
	
	public SceneManager() {
		this.ka = new KeyAdapter();
		GlobalScreen.addNativeKeyListener(ka);
		this.menu = new MenuScene(this);
		this.game  = new GameScene(this);
	}
	
	public void swapToScene(IScene scene) {
		if (this.currentScene != null) currentScene.unloadScene();
		this.currentScene = scene;
		this.currentScene.loadScene();
	}
	
	public KeyAdapter getKeyAdapter() { return this.ka; }
	
	public GameScene getGame() { return this.game; }
	public MenuScene getMenu() { return this.menu; }
	
	public IScene getCurrentScene() { return this.currentScene; }
	
}
