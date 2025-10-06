package hangman;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

public class WordPanel extends JPanel{

	private static ArrayList<String> wordArray;
	private static ArrayList<String> displayArray;
	private static JLabel text;
	private Font font;
	private static String wordDisplay;
	
	//WordPanel constructor (receives string parameter 'word' from MainWindow to structure an appropriate display to the user)
	//WordPanel mainly focuses on the word panel as it only contains how the word panel is structured and how it changes as the user plays the game ensuring high cohesion
	WordPanel(String word){
		setLayout(new FlowLayout());
		setVisible(true);
		font = new Font("Sans Serif", Font.PLAIN, 60);
		wordDisplay = ""; //word display initially set as blank
		wordArray = new ArrayList<>();
		displayArray = new ArrayList<>();
		
		//loops through each letter in the chosen random word sent as a parameter from MainWindow
		for(int i = 0; i < word.length(); i++) {
				wordArray.add(String.valueOf(word.charAt(i))); //wordArray stores each letter of the word in order
				displayArray.add("_"); //displayArray stores the same number of underscores as the number of letters in the word
		}
		
		//setWordDisplay() method sets wordDisplay to what will be displayed to the user using displayArray
		//initially, displayArray is only underscores therefore the user will only see underscores as they haven't guessed a letter yet
		//as displayArray has the same number of underscores as the length as the word, users initially have the hint of the length of the word
		setWordDisplay();
		
		text = new JLabel(wordDisplay, JLabel.CENTER); //after setting the wordDisplay, 'text' is a JLabel set with the label of the value of wordDisplay
		text.setFont(font); //font is set for text
		add(text); //text is added to the WordPanel
	}
	
	//setWordDisplay() method sets the wordDisplay value that will be displayed to the user
	public static void setWordDisplay() {
		
		//iterates through every letter of the word using wordArray 
		for(int i = 0; i < wordArray.size(); i++) {
			
			//if the letter is not the last letter of the word, the letter plus a space is added to the wordDisplay so that the word looks clear
			if (i < wordArray.size() - 1) {
				wordDisplay += displayArray.get(i)+" ";
            }
			//otherwise if the letter is the last letter only the last letter is added to ensure centering when word is displayed
			else {
            	wordDisplay += displayArray.get(i);
            }
			
		}
	}
	
	//guess method checks if letter guessed by user was right or wrong
	public static boolean guess(String letter){
		
		boolean guess = false; //guess in initially set as false
		
		//iterates through each letter in the word using wordArray
		for(int i = 0; i < wordArray.size(); i++) {
			
			//if the letter is equal to any letter within the wordArray, guess is set as true
			if(letter.equals(wordArray.get(i))) {
				guess=true;
				wordDisplay = ""; //wordDisplay is reset back to blank
				displayArray.set(i, letter); //displayArray is updated making the appropriate underscore(s) change to the correct letter guessed
				
				//setWordDisplay is called to set wordDisplay to a new value that will now have a new letter in place of one or more underscores
				setWordDisplay();
				
				//text JLabel is then set to the new value of the wordDisplay updating the display for the user to show that the letter they guessed was correct
				text.setText(wordDisplay);
			}
		}
		return guess;
	}
	
	//checkComplete() method checks if displayArray is equal to wordArray meaning that all the underscores have been changed to the appropriate letters making both arrays equivalent
	public static boolean checkComplete() {
		if(wordArray.equals(displayArray)) {
			return true; //will return true if both arrays are equal as the user has won since all the underscores have changed and all the letters have been guessed resulting in the full word being displayed to the user
		}
		return false; //if they are not equal, the user has not yet won so method returns false
	}
	
}
