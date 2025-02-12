
package cardGame;

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
