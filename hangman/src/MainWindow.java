package hangman;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class MainWindow extends JFrame{
	
	private static HealthPanel hp;
	private	static WordPanel wp;
	private	static ButtonPanel bp;
	private static String word;
	private static Container cpane;
	private static ArrayList <String> wordsList;
	
	//MainWindow constructor
	//all panels are separate from the MainWindow and each of the classes have specific methods appropriate to the class name and only when needed will reference other methods in other classes, this indicates low coupling
	MainWindow() throws IOException{
		cpane = getContentPane(); //makes a content pane for the main window to add panels to
		cpane.setVisible(true); //content pane to be visible
		cpane.setLayout(new GridLayout(3,1)); //has a 3,1 grid layout to allow, health panel, button panel and word panel to be displayed together vertically 
		
		
		
		//new HealthPanel is added to the content pane that will show the user's lives
		hp = new HealthPanel();
		cpane.add(hp);
		
		wordsList = new ArrayList<>();
		
		//BufferReader reads through all the words in wordList.txt (that contains a large list of words) and adds it to the wordList array list
		//each word is separated by a new line and all words are uppercase
		BufferedReader br = new BufferedReader(new FileReader("wordList.txt"));
		while(br.readLine()!=null) {
			wordsList.add(br.readLine());
		}
		br.close();
		
		//calls generateWord() method to make data field 'word' have assigned a value which will be one of the words in wordList
		generateWord();
		
		//new ButtonPanel is added to the content pane that will show the buttons the users can click on to guess the word
		bp = new ButtonPanel();
		cpane.add(bp);
		
		//new WordPanel is added to content pane that will show either blanks, some letters of the chosen word or the word itself for the user to reference while they guess
		wp = new WordPanel(word); //word is sent as a parameter to WordPanel
		cpane.add(wp);
		
		//WindowListener will terminate the program when the window is closed
		addWindowListener(
			new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		
	}
	
	//generates a new random word from the wordList
	public static void generateWord() {
		
		Random random = new Random(); //new random is instantiated
		word = wordsList.get(random.nextInt(wordsList.size())); //a word is picked randomly from wordsList and is assigned to the data field
		
	}
	
	//userLoses() method is called when the user runs out of lives opening a dialog box (using JOptionPane) for the user to indicate they lost
	public static void userLoses() {
		//dialog box will inform the user that they lost, will display the full correct word and asks if they want to try again 
		int option = JOptionPane.showConfirmDialog(null, "You have ran out of lives \n The word was " + word + "\n Would you like to try again?");
		//if user presses no to trying again on the dialog box, the program closes
		if(option == JOptionPane.NO_OPTION)
			System.exit(0);
		//if user presses yes, resetGame method is called resulting in the game restarting and a new word being generated
		if(option == JOptionPane.YES_OPTION)
			resetGame();
	}
	
	//userWins() method is called when the user guesses the word completely, opening a dialog box for them indicating that they won
	public static void userWins() {
		//dialog box will inform the user that they won and asks if they want to play again 
		int option = JOptionPane.showConfirmDialog(null, "Congratulations! You have guessed the word! \n Would you like to play again?");
		//if user presses no to playing again on the dialog box, the program closes
		if(option == JOptionPane.NO_OPTION)
			System.exit(0);
		//if user presses yes, resetGame is called resulting in the game restarting and a new word being generated
		if(option == JOptionPane.YES_OPTION)
			resetGame();
	}
	
	//restarts the game so user can play again
	public static void resetGame() {
		cpane.removeAll(); //removes all the panels that have been added to the content pane
		generateWord(); //generates a completely new random word
		cpane.add(new HealthPanel()); //adds a completely new HealthPanel to the content pane
		cpane.add(new ButtonPanel()); //adds a completely new ButtonPanel to the content pane
		cpane.add(new WordPanel(word)); //adds a completely new WordPanel to the content pane
		cpane.revalidate(); //revalidates content pane to restructure the layout of the game
		cpane.repaint(); //will update the visuals of the content pane so that the program restarts seamlessly
		
		
	}
	
}
