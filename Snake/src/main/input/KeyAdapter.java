package main.input;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyAdapter implements NativeKeyListener {
	
	private static final int UP_ARROW_KEY_CODE = 57416;
	private static final int LEFT_ARROW_KEY_CODE = 57419;
	private static final int DOWN_ARROW_KEY_CODE = 57424;
	private static final int RIGHT_ARROW_KEY_CODE = 57421;
	
	private InputManager im;
	
	public KeyAdapter(InputManager im) {
		this.im = im;
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
		// TODO Auto-generated method stub
		NativeKeyListener.super.nativeKeyPressed(nativeEvent);
		
		switch (nativeEvent.getKeyCode()) {
		case UP_ARROW_KEY_CODE:
			im.setDirection(InputManager.UP_DIRECTION);
			break;
		case DOWN_ARROW_KEY_CODE: 
			im.setDirection(InputManager.DOWN_DIRECTION);
			break;
		case LEFT_ARROW_KEY_CODE:
			im.setDirection(InputManager.LEFT_DIRECTION);
			break;
		case RIGHT_ARROW_KEY_CODE:
			im.setDirection(InputManager.RIGHT_DIRECTION);
			break;
		}
	}
	
}
