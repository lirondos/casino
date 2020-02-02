/*
Name: Elena Alvarez Mellado
Email: ealvarezmellado@brandeis.edu
Date: December 10 2018
The Card class creates an object of the class Card.  
*/
import java.util.*;

public class Card{
	/*
	Cards have a suit, a value, a color and a special name (that can be null). 
	Cards can return their value, their color, their suit, print themselves, return whether they have a special name, compare their value to another card.
	*/
	String suit;
	int value;
	String color;
	String specialName;
	/*
	The class constructor for the card object
	*/
	public Card(int value, String suit){
		this.value = value;
		this.suit = suit;
		// We get the cards color
		color = createColor();
		// We create specialName 
		specialName = createSpecialName();
	}
	/*
	The instance method getValue returns the value of the card
	*/
	public int getValue(){
		return value;
	}
	/*
	The instance method getBlackjackValue returns the value of the card in Blackjack game (returns 1 for ace)
	*/
	public int getBlackjackValue(){
		int value = 0;
		if(getValue()>10){
			value = 10;
		}else{
			value = getValue();
		}
		return value;
	}
	/*
	The instance method getColor returns the color of the card
	*/
	public String getColor(){
		return color;
	}
	public String getSuit(){
		return suit;
	}
	/*
	The instance method toString builds a string with the card info
	*/
	public String toString(){
		if(this.hasSpecialName()){
			return specialName + " of " + suit;
		}else{
			return value + " of " + suit;
		}
	}
	/*
	The instance method hasSpecialName returns whether the value of the card has a special method (ace, king, etc). 
	*/
	public boolean hasSpecialName(){
		if(specialName == null){
			return false;
		}else{
			return true;
		}
	}
	/*
	The instance method isHigherThan returns whether the value of the card is higher than the value of the card that is passed as parameter. 
	*/
	public boolean isHigherThan(Card otherCard){
		if(this.value>otherCard.value){
			return true;
		}else{
			return false;
		}
	}
	/*
	The instance method createSpecialName returns the special name that corresponds to the card value. Returns null if card does not have a special name.
	*/
	public String createSpecialName(){
		String specialName;
		if(value == 1){
			specialName = "Ace";
		}else if(value == 11){
			specialName = "Jack";
		}else if(value == 12){
			specialName = "Queen";
		}else if(value == 13){
			specialName = "King";
		}else{
			specialName = null;
		}
		return specialName;
	}
	/*
	The instance method createColor returns the special color that corresponds to the card suit. 
	*/
	public String createColor(){
		String color; 
		if(suit == "Hearts" || suit == "Diamonds"){
			color = "red";
		}else{
			color = "black";
		}
		return color;
	}
}