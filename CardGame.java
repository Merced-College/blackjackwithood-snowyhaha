package cardGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Card {
    private String suit;
    private String name;
    private int value;
    private String picture;

    public Card(String suit, String name, int value, String picture) {
        this.suit = suit;
        this.name = name;
        this.value = value;
        this.picture = picture;
    }

    public String getSuit() { return suit; }
    public void setSuit(String suit) { this.suit = suit; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    @Override
    public String toString() {
        return "Card{suit='" + suit + "', name='" + name + "', value=" + value + ", picture='" + picture + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value == card.value;
    }
}

class CardGame {
    private static ArrayList<Card> deckOfCards = new ArrayList<>();
    private static ArrayList<Card> playerCards = new ArrayList<>();
    private static int playerScore = 0;

    public static void main(String[] args) {
        Scanner input = null;
        try {
            input = new Scanner(new File("cards.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Error reading cards file");
            e.printStackTrace();
            return;
        }

        while(input.hasNext()) {
            String[] fields = input.nextLine().split(",");
            Card newCard = new Card(fields[0], fields[1].trim(),
                    Integer.parseInt(fields[2].trim()), fields[3]);
            deckOfCards.add(newCard);    
        }

        shuffle();

        for(int i = 0; i < 5; i++) {
            playerCards.add(deckOfCards.remove(0));
        }

        System.out.println("Your cards:");
        for(Card c: playerCards)
            System.out.println(c);
        calculateScore();
        System.out.println("Final Score: " + playerScore);
    }

    public static void shuffle() {
        for (int i = 0; i < deckOfCards.size(); i++) {
            int index = (int) (Math.random()*deckOfCards.size());
            Card c = deckOfCards.remove(index);
            deckOfCards.add(c);
        }
    }

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
        playerScore = 0;
        if(checkFor2Kind()) {
            playerScore += 2;
            System.out.println("Found a pair! +2 points");
        }

        for(Card card : playerCards) {
            if (card.getValue() >= 10) {
                playerScore += 1;
                System.out.println("High card: " + card.getName() + " (+1 point)");
            }
        }

        if (playerScore == 0) {
            System.out.println("No points scored this hand.");
        }
    }
}