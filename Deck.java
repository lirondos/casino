/*
Name: Elena Alvarez Mellado
Email: ealvarezmellado@brandeis.edu
Date: December 10 2018
The Deck class creates an object of the class Card.  
*/
import java.util.*;

public class Deck{
	/*
	Decks have an active pile, a discard pile, a number of active cards (cards within the active pile) and number of discarded cards (cards within the inactive pile).
	*/
	public static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
	public static final int CARDS_IN_A_DECK = 52;
	public static final int CARDS_IN_A_SUIT = 13;
	Card[] activePile;
	Card[] discardPile;
	int numberOfActiveCards;
	int numberOfDiscardedCards;
	/*
	The class constructor for the deck object. It creates an active pile (array of cards with 52 slots and populates them), an inactive pile  (array of cards with 52 slots) and a counter for both arrays that count the number of cards within each array. It finally shuffles the active pile.  
	*/
	public Deck(){
		activePile = new Card[CARDS_IN_A_DECK];
		populate();
		discardPile = new Card[CARDS_IN_A_DECK];
		numberOfActiveCards = CARDS_IN_A_DECK;
		numberOfDiscardedCards = 0;
		shuffle();
	}
	/*
	The instance method toString returns the deck as a string 
	*/
	public String toString(){
		String myString = "Number of cards in the deck: " + numberOfActiveCards;
		for(int i = 0; i < activePile.length; i++){
			Card card = activePile[i];
			if(card != null){
				myString = myString + "\n" + card.toString();
			}
		}
		return myString;
	}
	
	/*
	The instance method shuffle shuffles the active pile of the deck, avoiding the null slots within the array.  
	*/
	public void shuffle(){
		Random rand = new Random();
		for(int i = 1; i<CARDS_IN_A_DECK; i++){
			if(activePile[i] != null){
				int j;
				do{
					j = rand.nextInt(i + 1);
				} while(activePile[j]==null);
				swap(i, j);
			}
		}
	}
	/*
	The instance method drawNextCard takes the next card out of the active pile of the deck. It first checks if there are any cards left in the active pile by checking the counter numberOfActiveCards. If numberOfActiveCards is zero, then it reorganizes the deck and takes the first card on the pile. If numberOfActiveCards is not zero (that is, if there are still cards available in the active pile), then it takes the next one. Because cards are always drawn in order, the next available card will be in the index CARDS_IN_A_DECK - numberOfActiveCards of the active pile.
	
	Once a card is drawn, the slots goes to null. 
	*/
	public Card drawNextCard(){
		int position;
		if(numberOfActiveCards == 0){
			reorganizeDeck();
			position = 0;
		}else{
			position = CARDS_IN_A_DECK - numberOfActiveCards;
		}
		Card myCard = activePile[position];
		activePile[position] = null;
		numberOfActiveCards--;
		return myCard;
	}
	/*
	The instance method reorganizeDeck is called when there is no available card left in the active card. This method copies the pile of cards from the discarded pile, creates a new empty pile of cards for the discardPile and reset the counters.
	*/
	public void reorganizeDeck(){
		activePile = discardPile.clone();
		numberOfActiveCards = numberOfDiscardedCards;
		discardPile = new Card[52];
		numberOfDiscardedCards = 0;
		shuffle();
	}	
	/*
	The instance method discard takes a card and puts it on the discarded pile. The counter for numberOfDiscardedCards is increased by one.
	*/
	
	public void discard(Card c){
		discardPile[numberOfDiscardedCards] = c;
		numberOfDiscardedCards++;
	}
	/*
	The instance method swap takes two index from the active pile and swap the cards in those positions.
	*/
	public void swap(int i, int j){
		Card temp = activePile[i];
		activePile[i] = activePile[j];
		activePile[j] = temp;
	}
	/*
	The instance method populate takes the empty activePile and populates it with all the cards in a deck 
	*/
	public void populate(){
		for(int i = 0; i<SUITS.length; i++){
			for(int cardValue = 1; cardValue<=CARDS_IN_A_SUIT; cardValue++){
				Card newCard = new Card(cardValue, SUITS[i]);
				activePile[(cardValue-1)+CARDS_IN_A_SUIT*i] = newCard;
			}
		}
	}
}