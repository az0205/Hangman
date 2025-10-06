package hangman;

import java.awt.*;
import javax.swing.*;

public class HealthPanel extends JPanel{
	
	private static int livesLeft;
	private static JLabel [] lives;
	private static Font font;
	
	//HealthPanel mainly focuses on the health panel and how it may change and nothing else ensuring high cohesion
	HealthPanel(){
		
		livesLeft = 7; //livesLeft starts off with being 7 as that is the number of lives the user will have at the start of the game
		setVisible(true); //sets the panel to be visible
		setLayout(new GridLayout(1,livesLeft,4,1)); //sets a grid layout for the lives that will be added to the HealthPanel forming a horizontally display of the number of lives, 4,1 indicates the spacing between them
		font = new Font("Sans Serif", Font.PLAIN, 30); //font is specified
		lives = new JLabel[livesLeft]; //lives array is a JLabel array, so that number can be displayed, whose length is the same as the initial lives left
		
		
		//loops through all the lives in lives array and sets their appearance
		for(int i = 0; i < lives.length; i++) {
			lives[i] = new JLabel((String.valueOf(i+1)), JLabel.CENTER); //each life block will contain their number in the center starting from 1 and ending at 7
			lives[i].setBackground(Color.GREEN); //background is set as green
			lives[i].setOpaque(true); //set as opaque
			lives[i].setFont(font); //font is set
			add(lives[i]); //each life from the lives array is finally added to the HealthPanel
		}
	}
	
	//removeLife() method will remove one life from the total lives
	public static boolean removeLife() {
		lives[livesLeft-1].setBackground(Color.RED); //The last most green colored life is set as the color red indicating it has been removed
		livesLeft--; //lives left decrements so that when method is called again, the next last life is changed to red
		
		//if all the lives have been lost, removeLife() will return false otherwise true
		if(livesLeft==0) {
			return false;
		}
		return true;
	}
	
}
