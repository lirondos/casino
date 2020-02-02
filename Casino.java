/*
Name: Elena Alvarez Mellado
Email: ealvarezmellado@brandeis.edu
Date: December 10 2018
The Casino program simulates the playWar and the blackjack game.
*/

import java.util.*;

public class Casino{
	public static void main(String[] args){
		chooseGame();
	}
	
	/*
	The playBlackjack method simulates the blackjack game. It asks the user for the money they have, the bet they want to make and draws two cards for the player. The player chooses if they want another card (up until they reach 21, they get 5 five cards or the say the dont want another card). Then the computer plays. Whoever has a higher value that is 21 or less, wins. 
	
	For simplicity, the ace value is always 1.
	*/
	public static void playBlackjack(){
		boolean wantToPlayBlackjack = true;
		double credit = getCredit();
		Deck myDeck = new Deck();
		while(wantToPlayBlackjack && credit>0){
			double bet;
			do{
				bet = makeBet();
			} while(bet>credit || bet<=0);
			// We draw the first two cards
			Card playerCard1 = myDeck.drawNextCard();
			Card playerCard2 = myDeck.drawNextCard();
			int humanTotal = playerCard1.getBlackjackValue() + playerCard2.getBlackjackValue();
			System.out.println("Human cards: ");
			System.out.println(playerCard1.toString());
			printCardAndPoints(playerCard2,humanTotal);
			int cardCounter = 2;
			boolean wantAnotherCard;
			do{
				wantAnotherCard = wantAnotherCard();
				// We draw a new card if the user says yes and add its points to the sum
				if(wantAnotherCard){
					Card newPlayerCard = myDeck.drawNextCard();
					humanTotal = humanTotal + newPlayerCard.getBlackjackValue();
					printCardAndPoints(newPlayerCard, humanTotal);
					cardCounter++;
					myDeck.discard(newPlayerCard);
				}
			}while(wantAnotherCard && cardCounter<=5 && humanTotal<21); // no more than 5 cards, not if the total is already 21 or +
			Card computerCard1 = myDeck.drawNextCard();
			Card computerCard2 = myDeck.drawNextCard();
			int computerTotal = computerCard1.getBlackjackValue() + computerCard2.getBlackjackValue();
			System.out.println("Computer cards: ");
			System.out.println(computerCard1.toString());
			printCardAndPoints(computerCard2, computerTotal);
			// While the computer total is less than 16, it draws another card
			while(computerTotal<16){
				Card newComputerCard = myDeck.drawNextCard();
				computerTotal = computerTotal + newComputerCard.getBlackjackValue();
				printCardAndPoints(newComputerCard, computerTotal);
				myDeck.discard(newComputerCard);
			}
			// We discard the first cards to avoid running out of cards
			myDeck.discard(playerCard1);
			myDeck.discard(playerCard2);
			myDeck.discard(computerCard1);
			myDeck.discard(computerCard2);
			// we check who won and get the new credit
			credit = getNewCredit(humanTotal, computerTotal, credit, bet);
			if (credit>0){
				wantToPlayBlackjack = wantToKeepPlaying();
			}else{
				System.out.println("I'm sorry, you ran out of money to keep on playing at Antonella's cyber casino. The house always wins!");
			}
		}
	}
	/*
	The wantAnotherCard method asks the user if they want another card. It returns true if yes, false if no
	*/
	public static boolean wantAnotherCard(){
		Scanner console = new Scanner(System.in);
		System.out.println("Do you want another card?");
		String reply = console.next();
		if(reply.charAt(0) == 'y' || reply.charAt(0) == 'Y'){
			return true;
		}else{
			return false;
		}
	}
	/*
	The printCardAndPoints method prints the card and the accumulated points so far
	*/
	public static void printCardAndPoints(Card myCard, int points){
		System.out.println(myCard.toString());
		System.out.println("That's " + points + " points");
	}
	/*
	The getNewCredit method sees who won the round, substracts or adds the bet and returns the new credit
	*/
	public static double getNewCredit(int humanTotal, int computerTotal, double credit, double bet){
		if(humanTotal<=21 && (humanTotal>computerTotal || computerTotal>21)){
			credit = credit + bet; 
			System.out.println("Human won. You get " + bet + " dollars. Now you have " + credit + " dollars.");
		}else if (computerTotal<=21 && (humanTotal<computerTotal || humanTotal>21)){
			credit = credit - bet; 
			System.out.println("Human lost. You lose " + bet + " dollars. Now you have " + credit + " dollars.");
		}else{
			System.out.println("Nobody wins! Your credit remains the same: " + credit + " dollars.");
		}
		return credit;
	}
	/*
	The playWar method simulates the card game war. The deck is created and two cards are drawn from it, one for the player, another for the computer. If the player's card is higher than the computer card, then the human wins the bet. Otherwise, the human loses the bet. 
	*/
	
	public static void playWar(){
		boolean wantToPlayWar = true;
		double credit = getCredit();
		Deck myDeck = new Deck();
		while(wantToPlayWar && credit>0){
			double bet;
			do{
				bet = makeBet();
			} while(bet>credit || bet<=0);
			Card playerCard = myDeck.drawNextCard();
			System.out.println("Human card: " + playerCard.toString());
			Card computerCard = myDeck.drawNextCard();
			System.out.println("Computer card: " + computerCard.toString());
			if(playerCard.isHigherThan(computerCard)){
				credit = credit + bet; 
				System.out.println("Human won. You get " + bet + " dollars. Now you have " + credit + " dollars.");
			}else{
				credit = credit - bet; 
				System.out.println("Human lost. You lose " + bet + " dollars. Now you have " + credit + " dollars.");
			}
			myDeck.discard(playerCard);
			myDeck.discard(computerCard);
			if (credit>0){
				wantToPlayWar = wantToKeepPlaying();
			}else{
				System.out.println("I'm sorry, you ran out of money to keep on playing at Antonella's cyber casino. The house always wins!");
			}
		}
	}
	/*
	The chooseGame method asks the user what game they want to play
	*/
	public static void chooseGame(){
		Scanner console = new Scanner(System.in);
		System.out.println("Please, type w for war and b for blackjack");
		String reply = console.next();
		if(reply.charAt(0)== 'w'){
			playWar();
		}else{
			playBlackjack();
		}
	}

	/*
	The makeBet method prompts the user to make the bet
	*/
	public static double makeBet(){
		Scanner console = new Scanner(System.in);
		System.out.println("Please, enter the bet you want to make. Remember that you must be capable of paying your debts, therefore no bets higher than your current credit are allowed (but some amount must be betted).");
		if(console.hasNextDouble()){
			return console.nextDouble();
		}else{
			return 0;
		}
	}
	/*
	The getCredit method asks the user how much money they have on them.
	*/
	public static double getCredit(){
		Scanner console = new Scanner(System.in);
		System.out.println("Please, enter the amount of money you have to spend at Antonella's cyber casino");
		return console.nextDouble();
	}
	/*
	The wantToKeepPlaying method asks the user if they want to keep on playing. This method will only be called if the user has enough credit to keep on playing. 
	*/
	public static boolean wantToKeepPlaying(){
		Scanner console = new Scanner(System.in);
		System.out.println("Want to play another round?");
		String reply = console.next();
		if(reply.charAt(0) == 'y' || reply.charAt(0) == 'Y'){
			return true;
		}else{
			System.out.println("Thank you for visiting Antonella's cyber casino. We hope to see you soon! Bye!");
			return false;
		}
	}
}