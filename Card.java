public class Card implements Comparable<Card>{
	
	// *** INSTANCE VARIABLES *** //
	private int suit; // use integers 1-4 to encode the suit
	/* clubs = 1
	 * diamonds = 2
	 * hearts = 3
	 * spades = 4
	*/
	private int rank; // use integers 1-13 to encode the rank
	/* ace = 1
	 * 2-10 = 2-10
	 * jack = 11
	 * queen = 12
	 * king = 13
	*/

	/** Constructor Card:
	* Assigns the suit and rank of the card
	* @param s the suit
	* @param r the rank
	**/
	public Card(int s, int r){
		suit = s;
		rank = r;
	}
	
	/** Overriden compareTo:
	* Compares cards to sort them
	* @param c the Card being compared
	* @return the number depending on it's rank/suit
	**/
	public int compareTo(Card c){
		// If the rank is bigger than the comparable card's, return the largest value
		if(rank > c.rank) {
			return 3;
		// If the rank is equal, check the suit
		} else if(rank == c.rank) {
			if(suit > c.suit) {
				return 2;
			} else if (suit == c.suit) {
				return 1;
			} else {
				return 0;
			}
		// If the rank is less than, return the lowest value
		} else {
			return -1;
		}
	}
	
	/** Overriden toString:
	* Uses display to return a tidy String
	* @return the card's display
	**/
	public String toString() {
		return display();
	}

	/** [ADDED] rank:
	* @param c the desired card
	* @return c's rank
	**/
	public int getRank() {
		return rank;
	}

	/** [ADDED] suit:
	* @param c the desired card
	* @return c's suit
	**/
	public int getSuit() {
		return suit;
	}

	/** [ADDED] display:
	* @return displayCard the String version of the card
	**/
	public String display() {
		// [ADDED] displayCard holds the String version of the card
		String displayCard = "";
		if(rank==1) {
			displayCard = "Ace of ";
		} else if(rank > 1 && rank <= 10) {
			displayCard = rank + " of ";
		} else if(rank == 11) {
			displayCard = "Jack of ";
		} else if(rank == 12) {
			displayCard = "Queen of ";
		} else if(rank == 13) {
			displayCard = "King of ";
		}
		if(suit == 1) {
			displayCard += "Clubs ♣";
		} else if(suit == 2) {
			displayCard += "Diamonds ♦";
		} else if(suit == 3) {
			displayCard += "Hearts ♥";
		} else if(suit == 4) {
			displayCard += "Spades ♠";
		}
		return displayCard;
	}

}
