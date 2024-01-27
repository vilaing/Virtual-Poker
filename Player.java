// Import utilities
import java.util.ArrayList;
import java.util.Collections;

public class Player {
	
    // *** INSTANCE VARIABLES *** //
	private ArrayList<Card> hand; // the player's cards
	private double bankroll; // the player's current tokens
    private double bet; // the player's bet of tokens

    /** Constructor Player: 
    * Initializes the ArrayList hand
    * Initializes the starting bankroll
    **/
	public Player(){		
        hand = new ArrayList<>();
        bankroll = 50;
	}

    /** Mutator addCard:
    * Adds a card to the player's hand
    * @param c the card being added
    **/
	public void addCard(Card c){
        hand.add(c);
	}

    /** Mutator removeCard:
    * Removes a card from the player's hand
    * @param c the card being removed 
    **/
	public void removeCard(Card c){
        hand.remove(c);
    }

    /** [ADDED METHOD] getCard:
    * Returns the card at any given position
    * @param position the position of the desired card
    * @return Card the card at the position
    **/
    public Card getCard(int position) {
        return hand.get(position);
    }
	
    /** Mutator bets:
    * Sets the bet and updates the bankroll
    * @param amt the amount of tokens being bet
    **/
    public void bets(double amt){
        bet = amt;
        bankroll -= bet;
    }

    /** Mutator winnings:
    * Calculates the correct payout and updates the bankroll
    * @param odds the payout from the hand
    **/
    public void winnings(double odds){
        bankroll += bet*odds;
    }

    /** Accessor getBankroll:
    * @return bankroll the current bankroll
    **/
    public double getBankroll(){
        return bankroll;
    }

    /** [ADDED METHOD] showHand:
    * Prints the current hand of the player
    * Uses the toString method in Card.java with the position
    **/
    public void showHand() {
        System.out.println("Your current hand is:");
        for(int i = 1; i <= 5; i++) {
            System.out.println("["+i+"] " + hand.get(i-1));
        }
    }
    
    /** [ADDED METHOD] sortHand:
    * Sorts the current hand for evaluation
    * Uses the Collections.sort method and the compareTo method in Card.java
    * @return hand the sorted hand
    **/
    public ArrayList<Card> sortHand() {
        Collections.sort(hand);
        return hand;
    }

}


