package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// The class I created for the card game
class Card {
	// declares the variables for this class
		private String suit;
		private String name;
		private int value;
		private String picture;
		// creates the constructor for the class
		public Card(String suit, String name, int value, String picture) {
				this.suit = suit;
				this.name = name;
				this.value = value;
				this.picture = picture;
		}
 		// creates the getters for the class
		public String getSuit() {
				return suit;
		}
		// creates the setters for the class 
		public void setSuit(String suit) {
				this.suit = suit;
		}
		// creates the getters for the class
		public String getName() {
				return name;
		}
		// creates the setters for the class
		public void setName(String name) {
				this.name = name;
		}
		// creates the getters for the class
		public int getValue() {
				return value;
		}
		// creates the setters for the class
		public void setValue(int value) {
				this.value = value;
		}
		// creates the getters for the class
		public String getPicture() {
				return picture;
		}
		// creates the setters for the class
		public void setPicture(String picture) {
				this.picture = picture;
		}
		// creates the toString method for the class
		@Override
		public String toString() {
				return "Card{" +
								"suit='" + suit + '\'' +
								", name='" + name + '\'' +
								", value=" + value +
								", picture='" + picture + '\'' +
								'}';
		}
		// creates the equals method for the class
		@Override
		public boolean equals(Object obj) {
				if (this == obj) return true;
				if (obj == null || getClass() != obj.getClass()) return false;
				Card card = (Card) obj;
				return value == card.value;
		}
}

public class CardGame {
	// feature I added: score tracking
	private static ArrayList<Card> deckOfCards = new ArrayList<Card>();
	private static ArrayList<Card> playerCards = new ArrayList<Card>();
	// keep track of the player score
	private static int playerScore = 0;

	public static void main(String[] args) {

		Scanner input = null;
		try {
			input = new Scanner(new File("cards.txt"));
		} catch (FileNotFoundException e) {
			// error
			System.out.println("error");
			e.printStackTrace();
		}

		while(input.hasNext()) {
			String[] fields  = input.nextLine().split(",");
			//	public Card(String cardSuit, String cardName, int cardValue, String cardPicture) {
			Card newCard = new Card(fields[0], fields[1].trim(),
					Integer.parseInt(fields[2].trim()), fields[3]);
			deckOfCards.add(newCard);	
		}

		shuffle();

		//for(Card c: deckOfCards)
			//System.out.println(c);

		//deal the player 5 cards
		for(int i = 0; i < 5; i++) {
			playerCards.add(deckOfCards.remove(0));
		}
		// printing the score
		System.out.println("Your cards:");
		for(Card c: playerCards)
			System.out.println(c);
		calculateScore();
		System.out.println("\nFinal Score: " + playerScore);

	}//end main

	public static void shuffle() {

		//shuffling the cards by deleting and reinserting
		for (int i = 0; i < deckOfCards.size(); i++) {
			int index = (int) (Math.random()*deckOfCards.size());
			Card c = deckOfCards.remove(index);
			//System.out.println("c is " + c + ", index is " + index);
			deckOfCards.add(c);
		}
	}

	//check for 2 of a kind in the players hand
	public static boolean checkFor2Kind() {
		for(int i = 0; i < playerCards.size() - 1; i++) {
			Card current = playerCards.get(i);

			for(int j = i+1; j < playerCards.size(); j++) {
				Card next = playerCards.get(j);
				if(current.getValue() == next.getValue()) {
					System.out.println("Found pair: " + current.getName() + " and " + next.getName());
					return true;
				}
			}
		}
		return false;
	}

	public static void calculateScore() {
		// Reset score before calculating
		playerScore = 0;

		// Award points for pairs
		if(checkFor2Kind()) {
			playerScore += 2;
			System.out.println("Found a pair! +2 points");
		}

		// Award points for high cards (including Ace)
		for(Card card : playerCards) {
			int value = card.getValue();
			if (value >= 10) { // Include 10s and face cards
				playerScore += 1;
				System.out.println("High card: " + card.getName() + " (+1 point)");
			}
		}

		if (playerScore == 0) {
			System.out.println("No points scored this hand.");
		} else {
			System.out.println("Total score: " + playerScore);
		}
	}
}//end class