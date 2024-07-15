package test3;

// This class represents one playing card.
public class Card {
	// Card suits (provided for your convenience - use is optional)
	public static final int SPADES = 0;
	public static final int HEARTS = 1;
	public static final int CLUBS = 2;
	public static final int DIAMONDS = 3;
	// Card faces (provided for your convenience - use is optional)
	public static final int ACE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE = 9;
	public static final int TEN = 10;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	// define fields here
	int suit;
	int face;

	// This constructor builds a card with the given suit and face, turned face
	// down.
	public Card(int cardSuit, int cardFace) {
		// fill in this method
		this.suit = cardSuit;
		this.face = cardFace + 1;
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit() {
		return suit;
	}

	public String printgetSuit() {
		switch (getSuit()) {
		case 0:
			return "spades";
		case 1:
			return "hearts";
		case 2:
			return "clubs";
		case 3:
			return "diamond";
		default:
			return "";
		}
	}

	// This method retrieves the face (ace through king) of this card.
	public int getFace() {
		// fill in this method
		return face;
	}

	public void setValue(int val) {
		this.face = val;
	}

	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue() {
		switch (face) {
		case 2, 3, 4, 5, 6, 7, 8, 9, 10:
			return face;
		case 11, 12, 13:
			return 10;
		case 1:
			return 11;
		default:
			return face;
		}
	}
}
