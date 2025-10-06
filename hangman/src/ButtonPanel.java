package hangman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonPanel extends JPanel implements ActionListener{
	
	private static JButton[] alphabet;
	private Font font;
	
	//ButtonPanel focuses mainly on the button panel and the responses it may give that may result in it calling a different method in a different class if it is not related to the panel indicating high cohesion
	ButtonPanel(){
		setLayout(new GridLayout(4,7)); //ButtonPanel has 4,7 grid layout to hold all 26 letters of the alphabet
		setVisible(true);
		font = new Font("Sans Serif", Font.PLAIN, 15);
		alphabet = new JButton[26]; //alphabet is a JButton array as each letter is a button the user clicks to guess the word
		
		//loops through all letters of the alphabet and adds them to the alphabet JButton array
		for(char l = 'A'; l <= 'Z'; l++) {
			int i = l - 'A'; //i is position of each letter
			alphabet[i] = new JButton(String.valueOf(l)); //letter is changed to a string and added to alphabet JButton array
			add(alphabet[i]); //each new JButton of each letter is added to the WordPanel
			alphabet[i].setFont(font); //sets font for the letters
			alphabet[i].addActionListener(this); //action listener is added so that program responds when user clicks on a button
		}
		
	}
	
	//actionPerformed method makes it so that the program responds when button is clicked
	public void actionPerformed(ActionEvent e) {
		//iterates through the alphabet
		for(int i = 0; i<alphabet.length; i++){
			//checks if the source is from a button with one of the letters of the alphabet
			if(e.getSource()==alphabet[i]) {
				
				alphabet[i].setEnabled(false); //the letter button is disabled so that it can't be clicked again
				boolean guess = WordPanel.guess(alphabet[i].getText()); //guess method from WordPanel is called with the letter of the button clicked sent as a string parameter and returns a boolean value
				
				//if the guess was incorrect the method returns false, if this was the case, removeLife from HealthPanel is called as a life needs to be removed for an incorrect guess
				if (!guess) {
					boolean continueGame = HealthPanel.removeLife(); //removeLife will return false if all lives are lost and true if not
					//if return value for removeLife was false, it indicates that all the lives have been lost therefore userLoses method is called
					if (!continueGame)
						MainWindow.userLoses();
				}
				
				//if the boolean guess value was true, indicating the guess was correct, the program would skip the previous 'if' statement where removeLife method was called
				else {
					//instead checkComplete is called to check if the full word has been guessed and it returns a boolean value
					boolean wordComplete = WordPanel.checkComplete();
					//if this boolean value is true, it indicates that the word has been completed and the user won therefore userWins method is called
					if (wordComplete)
						MainWindow.userWins();
				}
			}
		}
	}
}
