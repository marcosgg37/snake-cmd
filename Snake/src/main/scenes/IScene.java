package main.scenes;

import main.input.IInputManager;

public interface IScene {

	void loadScene();
	void unloadScene();
	String getName();
	IInputManager getInputManager();
	void run();
	
}
