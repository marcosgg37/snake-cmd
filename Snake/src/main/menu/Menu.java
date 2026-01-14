package main.menu;

public class Menu {
	
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
		System.out.println("============================");
		System.out.println("[Flecha arriba ^ / Flecha abajo v] para mover el cursor");
		System.out.println("[Flecha derecha >] para confirmar");
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
