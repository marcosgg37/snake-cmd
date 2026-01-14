package main.menu;

import java.util.Map;
import static java.util.Map.entry;

public class Menu {
	
	// TODO: refactorizar para que el output esté en su propia clase
	private Map<String, String> colors = Map.ofEntries(
			entry("red","\u001B[31m"),
			entry("blue", "\u001B[34m"),
			entry("green", "\u001B[32m"),
			entry("yellow", "\u001B[33m"),
			entry("white", "\u001B[37m"),
			entry("reset", "\u001B[0m")
		);
	
	/*
	private Map<String, String> bgColors = Map.ofEntries(
				entry("red","\u001B[41m"),
				entry("blue", "\u001B[44m"),
				entry("green", "\u001B[42m"),
				entry("yellow", "\u001B[43m"),
				entry("white", "\u001B[47m"),
				entry("reset", "\u001B[0m")
			);
	*/
	
	private String[] options;
	
	private int selectedOption = 0;
	private boolean isOptionConfirmed = false;
	
	public Menu(String[] options) {
		this.options = options;
	}

	public int getSelectedOption() { return selectedOption; }
	
	// Action methods
	public void moveCursorUp() {
		this.selectedOption = this.selectedOption == 0 ? options.length - 1 : this.selectedOption - 1;
	}
	
	public void moveCursorDown() {
		this.selectedOption = this.selectedOption == options.length - 1 ? 0 : this.selectedOption + 1;
	}
	
	public void confirmOption() {
		this.isOptionConfirmed = true;
	}
	
	public void reset() {
		this.isOptionConfirmed = false;
	}
	
	public void draw() {
		System.out.print("\033[H\033[2J");
		System.out.println(colors.get("yellow") + "[Flecha arriba ^ / Flecha abajo v]" + colors.get("white") + " para mover el cursor");
		System.out.println(colors.get("yellow") + "[Flecha derecha >]" + colors.get("white") + " para confirmar");
		System.out.println();
		System.out.println("============================");
		System.out.println("Selecciona una opción:");
		for (int i = 0; i < options.length; i++) {
			if (selectedOption == i) System.out.print(">>> ");
			System.out.println(options[i]);
		}
		System.out.println("============================");
	}
	
	public boolean isOptionConfirmed() {
		return this.isOptionConfirmed;
	}
	
}
