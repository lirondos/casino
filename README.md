# casino
An implementation in Java of a casino program that simulates the war and the blackjack game.

The code consists of 2 classes (Card and Deck) and a client code Casino with the main method. 

###### The Card class ######
Cards have a suit, a value, a color and a special name that represents special names (like "Queen of Hearts"). All cards have a suit, a value and a colour, but not all cards have a special name. Cards that do not have a special name are null. In terms of behaviour, cards can return their value, their color, their suit, print themselves, return whether they have a special name, compare their value to another card and return their value according to the Blackjack game.

###### The Deck class ######
Decks have an active pile, a discard pile, a number of active cards (cards within the active pile) and number of discarded cards (cards within the inactive pile). The number of active cards and discarded cards keeps control of how many cards are still in the active pile and the discarded pile so that we know when reshuffling is needed. 

Whenever a new deck is built, we are creating a pile of active cards (new array of cards with 52 slots that gets populated with the 52 cards of the pocker deck), an inactive pile (an array of cards with 52 slots where discarded cards are accumulated) and a counter for both arrays that count the number of cards within each array. The new deck is finally shuffled (so that the cards in the active pile are not in order). Decks can print themselves, shuffle, draw a new card from the active pile, reorganize the pile, discard a card.

The instance method drawNextCard takes the next card out of the active pile of the deck. It first checks if there are any cards left in the active pile by checking the counter numberOfActiveCards. If numberOfActiveCards is zero, then it reorganizes the deck and takes the first card on the pile. If numberOfActiveCards is not zero (that is, if there are still cards available in the active pile), then it takes the next one. Because cards are always drawn in order, the next available card will be in the index CARDS_IN_A_DECK - numberOfActiveCards of the active pile. Once a card is drawn, the slots goes to null. 

The instance method reorganizeDeck is called when there is no available card left in the active card. This method copies the pile of cards from the discarded pile, creates a new empty pile of cards for the discardPile and reset the counters.

###### The client code ######
Two games can be played from the client code at the Casino.java file: war and blackjack. When the Casino program is called, the user is asked which of the two the player wants to play.

The playWar method simulates the card game war. The deck is created and two cards are drawn from it, one for the player, another for the computer. If the player's card is higher than the computer card, then the human wins the bet. Otherwise, the human loses the bet. 
	
The playBlackjack method simulates the blackjack game, although in a simplified way that considers that the ace is always 1. The game starts with two cards being drawn for the player. The player chooses if they want another card (up until they reach 21, they get 5 five cards or the say the dont want another card). Then the computer plays. Whoever has a higher value that is 21 or less, wins. Whoever gets more than 21, loses. If both the player and the computer go beyond 21, it's considered a tie. 

In both games, the user is asked at the begining how much money they have to spend and the bet they want to make. No bets equal to 0 or higher than the current credit are allowed. If the user loses all the money, he/she gets kicked out of the casino. 

There are two design problems that are not covered for the blackjack game but that would require improvement: 
1. The ace is always 1. In real blackjack, the ace can be 11 or 1 depending on the cards. This rule is not implemented in this version.
2. In order to avoid running out of cards, all the cards that are drawn at the user's request get discarded after their value is computed (while the two initial cards get discarded at the end of the game). However, this does not fully represent the way a blackjack is played. Cards should be discarded only when the game is over, not while the hand is still being played. In order to represent this, we would probably need to create and array to keep track of the cards that are being played, an array that would get discarded only once the game is over. We could even consider the possibility of implementing a new class Hand that represents the set of cards that are being played. 


	


