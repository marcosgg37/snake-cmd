package main.input;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class KeyAdapter implements NativeKeyListener {
	
	// Key codes for JNativeHook
	private static final int UP_ARROW_KEY_CODE = 57416;
	private static final int LEFT_ARROW_KEY_CODE = 57419;
	private static final int DOWN_ARROW_KEY_CODE = 57424;
	private static final int RIGHT_ARROW_KEY_CODE = 57421;
	
	private static final int W_KEY_CODE = 17;
	private static final int A_KEY_CODE = 30;
	private static final int S_KEY_CODE = 31;
	private static final int D_KEY_CODE = 32;
	
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
		case W_KEY_CODE:
			im.setDirection(InputManager.UP_DIRECTION);
			break;
		case DOWN_ARROW_KEY_CODE:
		case S_KEY_CODE:
			im.setDirection(InputManager.DOWN_DIRECTION);
			break;
		case LEFT_ARROW_KEY_CODE:
		case A_KEY_CODE:
			im.setDirection(InputManager.LEFT_DIRECTION);
			break;
		case RIGHT_ARROW_KEY_CODE:
		case D_KEY_CODE:
			im.setDirection(InputManager.RIGHT_DIRECTION);
			break;
		}
	}
	
}
