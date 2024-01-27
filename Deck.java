// Import utilities
import java.util.Random;

public class Deck {
	
	// *** INSTANCE VARIABLES *** //
	private Card[] cards = new Card[52]; // an array of 52 Card objects
	private int top; // the index of the top of the deck
	
	/** Constructor Deck:
	* Initializes each Card in the array according to a suit and rank
	**/
	public Deck(){
		// [ADDED] counter keeps track of the position in the array
		int counter = 0;
		// Add each suit
		for(int s = 1; s <= 4; s++) {
			// Add each rank in the suit
			for(int r = 1; r <= 13; r++) {
				// Initialize each Card in the array
				cards[counter] = new Card(s, r);
				counter++;
			}
		}
	}
	
	/** Mutator shuffle:
	* Randomly swaps two cards 1000 times
	* Uses a very basic temporary variable swapping method
	**/
	public void shuffle(){
		// [ADDED] Random object to store each random integer
		Random r = new Random();
		for(int i = 0; i<=1000; i++) {
			// [ADDED] card1 and card2, the cards being swapped depending on r
			int card1 = r.nextInt(52);
			int card2 = r.nextInt(52);
			// [ADDED] temp stores the temporary card before being swapped
			Card temp = cards[card1];
			cards[card1] = cards[card2];
			cards[card2] = temp;
		}
	}
	
	/** Accessor deal:
	* @return the top card
	**/
	public Card deal(){
		top++;
		return cards[top-1];
	}
	
}
