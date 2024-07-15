package test3;

import java.util.ArrayList;

//Player class that stores player methods and variables related to cards, printing cards, score, bank roll, if they are busted, if they are allowed
//to hit
public class Player {
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private int score;
	private boolean turn;
	private int bankRoll = 200;
	private int wager_amt = 0;
	private int ace11Count = 0;
	private int ace1Count = 0;

	public Player() {
		this.score = 0;
		this.turn = true;
	}

	public void add_card(Card c) {
		playerCards.add(c);
		this.score += c.getValue();
		if (c.getValue() == 11) {
			this.ace11Count++;
		}
	}

	public void face_up(Card c) {
		System.out.println("New Card has a value of: " + c.getValue());
	}

	public int card_val(int indx) {
		return playerCards.get(indx).getValue();
	}

	public int get_score() {
		if (this.ace1Count > 0 && this.score + 10 <= 21) {
			this.ace1Count--;
			this.ace11Count++;
			this.score += 10;
			System.out.println("By the way your Ace's (or at least one of them) value is currently at 1");
		}

		return this.score;
	}

	public boolean getTurn() {
		return this.turn;
	}

	public int get_bank_roll() {
		return this.bankRoll;
	}

	public void add_bank_roll() {
		this.bankRoll += (this.wager_amt * 2);
	}

	public void setTurn(boolean eligible) {
		this.turn = eligible;
	}

	public void set_wager_amt(int wager_amt) {
		this.wager_amt = wager_amt;
		this.bankRoll -= wager_amt;
	}

	public void draw() {
		this.bankRoll += this.wager_amt;
	}

	public void isBusted() {
		if (this.score > 21) {
			if (this.ace11Count > 0) {
				this.score -= 10;
				this.ace11Count -= 1;
				this.ace1Count += 1;
				System.out.println("You got lucky, your ace now has a value of 1");
				System.out.println("Your current score is now: " + this.get_score());
			} else {
				System.out.println("You busted, your score is greater than 21");
				this.setTurn(false);
				this.score = 0;
			}
		}
	}

	public boolean isBustedbool() {
		if (this.score > 21 || this.score == 0) {
			return true;
		}
		return false;
	}

//	public void wager() {
//		this.bankRoll += this.wager_amt;
//	}

	public void reset() {
		this.setTurn(true);
		playerCards.clear();
		this.score = 0;
		this.ace11Count = 0;
		this.ace1Count = 0;
	}

	public void printingCardValues() {
		int amt11printed = this.ace11Count;
		int amt1printed = this.ace1Count;
		for (Card c : this.playerCards) {
			if (c.getValue() == 11) {
				if (amt11printed > 0) {
					System.out.println("Ace with a value of " + 11 + " of suit " + c.printgetSuit());
					amt11printed--;
				} else if (amt1printed > 0) {
					System.out.println("Ace with a value of " + 1 + " of suit: " + c.printgetSuit());
					amt1printed--;
				}
			} else {
				System.out.println(c.getValue() + " of " + c.printgetSuit());
			}
		}
	}

}
