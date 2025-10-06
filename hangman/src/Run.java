package hangman;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class Run {
	
	public static void main(String[] args) throws IOException {
		
		MainWindow mw = new MainWindow(); //instantiates new MainWindow for the game
		mw.setSize(800,700); //sets size of the window
		mw.setTitle("Hangman"); //sets title of the window
		mw.setVisible(true); //sets the window to be visible
		
	}

}
