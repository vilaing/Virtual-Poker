Author: Violet Laing
Date: 11/23/2023

# Virtual-Poker
Thanks for checking out the game!

Virtual Poker instructions:
Place a bet then get dealt a hand of 5 cards.
Choose a card to redeal, but be careful, you can only redeal each card once!
Check to see if your hand is a winning one.

For this project in particular, you may select a testing mode:
   Enter your cards as command line arguments like so: java PokerTest d1 d10 d11 d12 d13

---

# PokerTest.java

- Contains the main method
- Allows for command line arguments
- Creates an object with the difficulty and starts the game

---

# Game.java

- Executes the game linearly
- Calls methods for the player to redeal
- Determines the winnings by checking the hand

---

# Player.java

- Prompts the user for a bet/redeal
- Passes the move back to Game

---

# Deck.java

- Creates the deck (initialization / shuffling)
- Deals/redeals the cards

---

# Card.java

- Initializes and formats cards
- Uses compareTo to compare card values
