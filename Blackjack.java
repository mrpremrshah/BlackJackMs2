package test3;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {
	public static Deck d1 = new Deck();
	public static int num_players;
	public static ArrayList<Player> players;
	public static boolean game = true;
	public static boolean curr_game = true;

	public static void main(String[] args) {
//		Creates an array list that stores all player objects
		players = new ArrayList<Player>();

//		Creates an array to store the indexes of the player list we have to remove (when checking if balance is 0) 
		ArrayList<Integer> remove_indx = new ArrayList<Integer>();

//		Initializes a scanner
		Scanner input = new Scanner(System.in);

		System.out.println("How many players are playing (excluding the dealer)");

//		Stores input of number of players in num_players variable
		num_players = input.nextInt();

//		For the amount of players that are playing, a new player is added to the players list
		for (int i = 0; i < num_players; i++) {
			players.add(new Player());
		}

//		A dealer is created with a player object
		Player dealer = new Player();

//		game -> boolean; if true round runs otherwise game ends
		while (game) {

//			Goes through the players array and checks if balance is 0
			for (Player p : players) {
				if (p.get_bank_roll() == 0) {
					remove_indx.add(players.indexOf(p));
					num_players--;
				}

//				If there are 0 players that have a balance greater than 0. then game ends
				if (num_players == 0) {
					System.out.println("Everyone lost (nobody has enough money), game over");
					System.exit(0);
				}
			}

//			Removes player that has a balance of 0
			for (int i = 0; i < remove_indx.size(); i++) {
				players.remove((int) remove_indx.get(i));
				System.out.println("Player" + (remove_indx.get(i) - 1)
						+ "was removed, your player numbers will be adjusted accordingly");
			}

//			For loop that asks each player to input a wager
			for (int i = 0; i < num_players; i++) {
				boolean wager_made = false;

//				Keeps asking player for wager until the wager is made successfully
				while (!wager_made) {
					System.out.println("How much would Player " + (i + 1) + " like to wager?: ");
					System.out.println("Your current balance is " + players.get(i).get_bank_roll());
					int wager_amt = input.nextInt();
//					Makes sure that the player can't wager more money than they have or less than or equal to 0
					if (wager_amt > players.get(i).get_bank_roll() || wager_amt <= 0) {
						System.out.println("Your input is invalid, make sure your amount is valid");
					} else {
//						If wager is a valid amount, the wager is made
						players.get(i).set_wager_amt(wager_amt);
						System.out.println("Congrats, your wager was made");
						wager_made = true;
					}
					System.out.println();
				}
			}

			System.out.println("The cards will now be dealt");

//			Gives each player the initial 2 cards and prints out their score
			for (int i = 0; i < num_players; i++) {
				Card c1 = d1.deal();
				Card c2 = d1.deal();
				players.get(i).add_card(c1);
				players.get(i).add_card(c2);

				System.out.print("Player " + (i + 1) + ": ");
				players.get(i).face_up(c1);

				System.out.print("Player " + (i + 1) + ": ");
				players.get(i).face_up(c2);

				System.out.println("Player" + (i + 1) + " score: " + players.get(i).get_score());

			}

//			Deals a card to the dealer
			Card c1 = d1.deal();
			dealer.add_card(c1);

//			If the dealer's first card is an ace. prints out ace instead of value so doesn't give hint about other card
			if (c1.getValue() == 11) {
				System.out.println("Dealer's first card is an ace");
			} else {
//				Simply prints out card value if not ace
				System.out.println("Dealer's first card's value: " + c1.getValue());
			}

//			Adds  hidden card to dealer 
			Card c2 = d1.deal();
			dealer.add_card(c2);

			System.out.println();

//			For each player continuously asks player to hit or stand
			for (int i = 0; i < num_players; i++) {
				System.out.println("Player " + (i + 1) + " score: " + players.get(i).get_score());
				while (!Done(players.get(i))) {
					if (players.get(i).getTurn()) {
						System.out.println("Here are your current card values");
						players.get(i).printingCardValues();

						System.out.println("Player " + (i + 1) + " would you like to hit or stand? ");
						String hit = input.next();
						if (hit.equalsIgnoreCase("hit")) {
							System.out.println("You chose to hit");
							hit(players.get(i));
						} else {
							System.out.println("You chose to stand");
							stand(players.get(i));
							continue;
						}
						System.out.println("Player " + (i + 1) + " score: " + players.get(i).get_score());
						players.get(i).isBusted();

						if (players.get(i).get_score() == 21) {
							System.out.println("You just got blackjack, congrats!");
						}
					}
				}
				System.out.println();

			}

			if (allZero()) {
				System.out.println("Dealer won because everyone busted");
			} else {
				System.out.println("Dealer's hidden card: " + dealer.card_val(1));

				while (dealer.get_score() <= 17) {
					System.out.print("Dealer's ");
					hit(dealer);
					if (dealer.get_score() > 17) {
						break;
					}
					dealer.isBusted();
				}

				System.out.println("Dealer's score: " + dealer.get_score());

				dealer.printingCardValues();

				if (dealer.isBustedbool()) {
					System.out.println("dealer busted");
					System.out.println("All players who didn't bust won");
					for (Player p : players) {
						if (!p.isBustedbool()) {
							System.out.println("Player: " + (players.indexOf(p) + 1) + " won");
							players.get(players.indexOf(p)).add_bank_roll();
						}
					}
				} else {
					for (Player p : players) {
						if (p.get_score() > dealer.get_score()) {
							System.out.println("Player " + (players.indexOf(p) + 1) + " won");
							players.get(players.indexOf(p)).add_bank_roll();
						} else if (p.get_score() == dealer.get_score() && p.get_score() != 0) {
							System.out.println("Player " + (players.indexOf(p) + 1) + " tied with the dealer");
							players.get(players.indexOf(p)).draw();
						} else {
							System.out.println("Dealer won against player " + (players.indexOf(p) + 1));
						}
					}

				}
			}

			System.out.println("Would you like to play again");
			if (input.next().equalsIgnoreCase("yes")) {
				for (Player p : players) {
					p.reset();
				}
				dealer.reset();
			} else {
				game = false;
				for (Player p : players) {
					System.out.println("Thanks for playing");
					System.out
							.println("Player " + (players.indexOf(p) + 1) + " has a bankroll of " + p.get_bank_roll());
				}
			}

		}
		input.close();
	}

	public static void hit(Player p) {
		Card c = d1.deal();
		p.add_card(c);
		p.face_up(c);
	}

	public static void stand(Player p) {
		p.setTurn(false);
		System.out.println("Player " + (players.indexOf(p) + 1) + " final score is " + p.get_score());
		System.out.println();
	}

	public static boolean Done(Player p) {
		if (p.getTurn()) {
			return false;
		}
		return true;
	}

	public static boolean allZero() {
		for (Player p : players) {
			if (p.get_score() != 0) {
				return false;
			}
		}
		return true;
	}

}
