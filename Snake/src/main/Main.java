package main;

import com.github.kwhat.jnativehook.GlobalScreen;

import main.scenes.SceneManager;

public class Main {
	
	public static void main(String[] args) throws Exception {
		/*
		im = new InputManager();
		
		GlobalScreen.addNativeKeyListener(new KeyAdapter(im));
		GlobalScreen.registerNativeHook();
		
		if (!(args.length == 0 || args.length == 1)) throw new Exception("Invalid argument amount");
		
		if (args.length == 1) g = new Game(im, Integer.parseInt(args[0]));
		else g = new Game(im);
		
		Thread gameThread = new Thread(null, g.getRunnable(), "Game");
		
		gameThread.start();
		
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlobalScreen.unregisterNativeHook();
		System.exit(0);
		*/
		
		if (!(args.length == 0 || args.length == 1)) throw new Exception("Invalid argument amount");
		
		SceneManager sm = new SceneManager();
		GlobalScreen.registerNativeHook();
		sm.swapToScene(sm.getMenu());
		GlobalScreen.unregisterNativeHook();
		
		System.exit(0);
		
	}
	
}
