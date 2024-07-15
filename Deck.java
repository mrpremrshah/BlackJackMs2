package test3;

import java.util.ArrayList;

// This class represents the deck of cards from which cards are dealt to players.
public class Deck {
	// define fields here
	ArrayList<Card> deck = new ArrayList<Card>();

	// This constructor builds a deck of 52 cards.
	public Deck() {
		// fill this method in
		refill();

	}

	public void refill() {
		int count = 0;
		for (int suit = 0; suit < 4; suit++) {
			for (int face = 0; face < 13; face++) {
				deck.add(count, new Card(suit, face));
				count++;
			}
		}
	}

	// This method takes the top card off the deck and returns it.
	public Card deal() {
		// fill this method in
		deck.remove(0);

		if (deck.isEmpty()) {
			refill();
		}

		shuffle();

		return deck.get(0);
	}

	// this method returns true if there are no more cards to deal, false otherwise
//	public boolean isEmpty() {
//		return deck.isEmpty();
//	}

	// this method puts the deck int some random order
	public void shuffle() {
		// fill this method in
		for (int i = 0; i < 1000; i++) {
			int random_first_indx = (int) (Math.random() * deck.size());
			int random_second_indx = (int) (Math.random() * deck.size());
			Card temp = deck.get(random_second_indx);
			deck.set(random_second_indx, deck.get(random_first_indx));
			deck.set(random_first_indx, temp);
		}
	}

}
