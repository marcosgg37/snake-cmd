package main;

import com.github.kwhat.jnativehook.GlobalScreen;

import main.game.Game;
import main.input.InputManager;
import main.input.KeyAdapter;
//import main.input.InputManager;

public class Main {
	
	private static Game g;
	private static InputManager im;
	
	public static void main(String[] args) throws Exception {
		
		im = new InputManager();
		
		GlobalScreen.addNativeKeyListener(new KeyAdapter(im));
		GlobalScreen.registerNativeHook();
		
		if (!(args.length == 0 || args.length == 1)) throw new Exception("Invalid argument amount");
		
		if (args.length == 1) g = new Game(im, Integer.parseInt(args[0]));
		else g = new Game(im);
		
		Thread gameThread = new Thread(null, g.getRunnable(), "Game");
		//Thread inputThread = new Thread(null, im.getRunnable(), "InputManager");
		
		gameThread.start();
		//inputThread.start();
		
		try {
			gameThread.join();
			//inputThread.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlobalScreen.unregisterNativeHook();
		System.exit(0);
		
	}
	
}
