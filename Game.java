// Import utilities
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
	
	// *** INSTANCE VARIABLES *** //
	private Player p; // the player object
	private Deck cards; // the deck object
	private Card testC; // [ADDED} the testing card object to create/access cards
	private Scanner s = new Scanner(System.in); // [ADDED] the scanner
	private int payout; // the payout from the hand
	private boolean test; // [ADDED] if the testing constructor was called or not
	private boolean again = true; // [ADDED] if the player wants to play again
	
	/** Constructor Game:
	* Overloaded constructor takes one parameter
	* Used to help test code
	* 	c = clubs
	* 	d = diamonds
	* 	h = hearts
	* 	s = spades
	* 	1-13 correspond to ace-king
	* 	example: s1 = ace of spades
    * 	example: testhand = {s1, s13, s12, s11, s10} = royal flush
	* Strips each String in the command line arguments
	* Creates a new Card and adds it to the player's hand
	* Instantializes all proper variables/objects
	* @param testHand a String array from the command line
	**/
	public Game(String[] testHand){
		// Checks to see if the array is the correct length, exits if not
		if(testHand.length != 5) {
			System.out.println("Invalid input. Must have a list length of 5.");
			System.exit(0);
		}
		// Initialize objects
		cards = new Deck();
		p = new Player();
		test = true;
		System.out.println("Current bankroll: " + p.getBankroll());
		// Iterate through each String in the array
		for(int i = 0; i <= 4; i++) {
			// Check to see which suit is specified in the first character -- clubs
			// (Process repeated for all following else if statements)
			if(testHand[i].charAt(0) == 'c') {
				// Check to see if the rank is a one or two digit number
				if(testHand[i].length() == 2) {
					// If it's one digit, take only the second character
					// Create a new Card of corresponding suit number and the rank
					// Add the card into the hand
					testC = new Card(1, Integer.parseInt(testHand[i].substring(1,2)));
					p.addCard(testC);
				} else {
					// If it's two digits, take the second and third characters
					// Create a new Card of corresponding suit number and the rank
					// Add the card into the hand
					testC = new Card(1, Integer.parseInt(testHand[i].substring(1,3)));
					p.addCard(testC);
				}
			// Repeat for diamonds
			} else if(testHand[i].charAt(0) == 'd') {
				if(testHand[i].length() == 2) {
					testC = new Card(2, Integer.parseInt(testHand[i].substring(1,2)));
					p.addCard(testC);
				} else {
					testC = new Card(2, Integer.parseInt(testHand[i].substring(1,3)));
					p.addCard(testC);
				}
			// Repeat for hearts
			} else if(testHand[i].charAt(0) == 'h') {
				if(testHand[i].length() == 2) {
					testC = new Card(3, Integer.parseInt(testHand[i].substring(1,2)));
					p.addCard(testC);
				} else {
					testC = new Card(3, Integer.parseInt(testHand[i].substring(1,3)));
					p.addCard(testC);
				}
			// Repeat for spades
			} else if(testHand[i].charAt(0) == 's') {
				if(testHand[i].length() == 2) {
					testC = new Card(4, Integer.parseInt(testHand[i].substring(1,2)));
					p.addCard(testC);
				} else {
					testC = new Card(4, Integer.parseInt(testHand[i].substring(1,3)));
					p.addCard(testC);
				}
			}
		}
	}
	
	/** Constructor Game:
	* Default constructor for a normal game
	* Initializes all variables/objects
	**/
	public Game(){
		// Initialize objects
		test = false;
		cards = new Deck();
		p = new Player();
		System.out.println("Current bankroll: " + p.getBankroll());
	}
	
	/** Mutator play:
	* Main method that iterates through every step of the game
	* Contains multiple loops to ensure valid input
	* Will repeat if desired
	**/
	public void play(){
		// again determines if the game will repeat
		while(again) {
			// Shuffles the deck
			cards.shuffle();
			// In a typical game, add 5 of the top cards to the player's hand
			// In a test game, the hand will have already been made
			if(test == false) {
				for(int i = 0; i<5; i++) {
					p.addCard(cards.deal());
				}
			}
			// Asks for a bet, continues until the input is valid
			while(true) {
				System.out.print("How many tokens would you like to bet (1-5)? ");
				int input = s.nextInt();
				// If the input is valid, call the bets method in Player.java and break
				if(input >= 1 && input <= 5 && input <= p.getBankroll()) {
					p.bets(input);
					break;
				// If the input is invalid, state why then ask again
				} else if(input > p.getBankroll()) {
					System.out.println("Invalid input, you do not have enough tokens for that.");
				} else {
					System.out.println("Invalid input, please enter a number from 1-5.");
				}
			}
			// Print the current hand
			p.showHand();
			// [ADDED] counter determines which cards have been added
			int counter = 0;
			// [ADDED] equalCards determines if the card is a duplicate
			boolean equalCards;
			// Continues until the user stops
			while(true) {
				System.out.print("Enter the position of the card you would like to remove" +
					" (-1 if none): ");
				int input = s.nextInt();
				// -1 sentinel to break the loop
				if(input == -1) {
					break;
				// If the input is valid, remove and add a card
				} else if(input >= 1 && input <= (5-counter)) {
					input--;
					counter++;
					System.out.println("Adding a new card.");
					// In a test game, make sure the card isn't a duplicate
					if(test == true) {
						equalCards = true;
						while(equalCards) {
							testC = cards.deal();
							// Iterate through each card to make sure the new one doesn't match
							for(int i = 0; i <= 4; i++) {
								if(testC.equals(p.getCard(i))) {
									equalCards = true;
								}
							}
							// If it doesn't, add the new card and remove the old
							if(equalCards == false) {
								p.removeCard(p.getCard(input));
								p.addCard(testC);
							}
						}
					// In a non-test game, add the new card and remove the old
					} else {
						p.removeCard(p.getCard(input));
						p.addCard(cards.deal());
					}
					p.showHand();
				// If the input is invalid, state why and ask again
				} else if(input > (5-counter) && input < 6) {
					System.out.println("Cannot remove a card that was redealt. Pick a " +
						"different card or enter -1 to stop.");
				} else {
					System.out.println("Not a valid input. Please enter a position from 1-5" +
						" or -1 if you don't want to remove a card.");
				}
			}
			// [ADDED] result stores the evaluation of checkHand, also sorts the hand
			String result = checkHand(p.sortHand());
			System.out.println("-----");
			p.showHand();
			System.out.println("You have a: " + result +"!");
			// Calculate the winnings
			p.winnings(payout);
			System.out.println("Current bankroll: " + p.getBankroll());
			// Ask if they want to play again with input validation
			while(true) {
				System.out.print("Play again (y/n)? ");
				String input = s.next();
				if(input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
					again = true;
					test = false;
					for(int i = 0; i <= 4; i++) {
						p.removeCard(p.getCard(0));
					}
					break;
				} else if(input.equalsIgnoreCase("no") || input.equalsIgnoreCase("n")) {
					again = false;
					break;
				} else {
					System.out.println("Invalid input, please enter y/n.");
				}
			}
		}
	}
	
	/** Evaluator checkHand:
	* Calls each method sequentially to see which is true
	* When a method evaluates to true, return the String type
	* Also updated the proper payout value
	* @param hand the ArrayList from the player's hand
	* @return result the correct String type
	**/
	public String checkHand(ArrayList<Card> hand){
		if(royalFlush(hand)) {
			payout = 250;
			return "Royal Flush";
		} else if(straightFlush(hand)) {
			payout = 50;
			return "Straight Flush";
		} else if(fourOfAKind(hand)) {
			payout = 25;
			return "Four of a Kind";
		} else if(fullHouse(hand)) {
			payout = 6;
			return "Full House";
		} else if(flush(hand)) {
			payout = 5;
            return "Flush";
        } else if(straight(hand)) {
			payout = 4;
            return "Straight";
        } else if(threeOfAKind(hand)) {
			payout = 3;
            return "Three of a Kind";
        } else if(twoPairs(hand)) {
			payout = 2;
            return "Two Pairs";
        } else if(onePair(hand)) {
			payout = 1;
            return "One Pair";
        }
		else {
			payout = 0;
			return "No Pair";
		}
	}
	
	/** *** ADDED METHODS ***
	* All methods function the same way with different calculations
	* The name of each method outlines its purpose
	* @param hand the ArrayList from checkHand
	* @return T/F depending on the evaluation
	**/

	public boolean royalFlush(ArrayList<Card> hand) {
		// System.out.println("Testing: " + hand.get(0).rank(hand.get(0)));
		// Make sure every card is the same suit
		for(int counter = 0; counter <= 4; counter++) {
			if(hand.get(0).getSuit() != hand.get(counter).getSuit()) {
				return false;
			}
		}
		// Check for the specific combination of ranks
		if(hand.get(0).getRank() == 1 && hand.get(1).getRank() == 10 
		&& hand.get(2).getRank() == 11 && hand.get(3).getRank() == 12 
		&& hand.get(4).getRank() == 13) {
			return true;
		} 
		return false;
	}

	public boolean straightFlush(ArrayList<Card> hand) {
		// Make sure every card is the same suit
		for(int counter = 0; counter <= 4; counter++) {
			if(hand.get(0).getSuit() != hand.get(counter).getSuit()) {
				return false;
			}
		}
		// Iterate through each card and see if the next card is one rank above
		for(int counter = 1; counter < 5; counter++) {
			if(hand.get(counter-1).getRank() != (hand.get(counter).getRank())-1) {    
                return false;
			}
		}
		return true;
	}

	public boolean fourOfAKind(ArrayList<Card> hand) {
		// See if there are four of the same ranks (made possible through sort)
		if(hand.get(0).getRank() == hand.get(3).getRank() 
		|| hand.get(1).getRank() == hand.get(4).getRank()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean fullHouse(ArrayList<Card> hand) {
		// Check each full house combination (made possible through sort)
		if(hand.get(0).getRank() == hand.get(2).getRank() 
		&& hand.get(3).getRank() == hand.get(4).getRank()) {
			return true;
		} else if(hand.get(0).getRank() == hand.get(1).getRank() 
		&& hand.get(2).getRank() == hand.get(4).getRank()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean flush(ArrayList<Card> hand) {
		// Check to see if each card is the same suit
		for(int counter = 0; counter <= 4; counter++) {
			if(hand.get(0).getSuit() != hand.get(counter).getSuit()) {
				return false;
			}
		}
		return true;
	}

    public boolean straight(ArrayList<Card> hand) {
		// Iterate through each card and see if the next card is one rank above
        for(int counter = 1; counter < 5; counter++) {
			if(hand.get(counter-1).getRank() != (hand.get(counter).getRank())-1) {
				// Special exception for ace straight
				if(hand.get(counter-1).getRank() == 1 && hand.get(counter).getRank() == 10) {
					continue;
				}
                return false;
			} 
		}
		return true;
    }

    public boolean threeOfAKind(ArrayList<Card> hand) {
		// Check each combination of threes (made possible with sort)
        if(hand.get(0).getRank() == hand.get(2).getRank() 
		|| hand.get(1).getRank() == hand.get(3).getRank() 
		|| hand.get(2).getRank() == hand.get(4).getRank()) {
			return true;
		} else {
			return false;
		}
    }

    public boolean twoPairs(ArrayList<Card> hand) {
		// [ADDED] pairs keeps track of the number of pairs
        int pairs = 0;
        for(int counter = 0; counter <4; counter++) {
			// Iterate through each card and note if a pair is made
            if(hand.get(counter).getRank() == hand.get(counter+1).getRank()) {
                pairs++;
            }
        }
        if(pairs == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onePair(ArrayList<Card> hand) {
		// Iterate through each card and check if a pair is found
        for(int counter = 0; counter <4; counter++) {
            if(hand.get(counter).getRank() == hand.get(counter+1).getRank()) {
                return true;
            }
		}
        return false;
    } 

}
