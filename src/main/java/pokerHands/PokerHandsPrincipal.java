package pokerHands;

import java.util.ArrayList;

import checkers.HandCombinaisonCheckerImpl;

public class PokerHandsPrincipal {

	/**
	 * Tableau des types de cartes
	 */
	private final static String SUITS[] = new String[] { "C", "D", "H", "S" };

	/**
	 * Tableau des types de cartes
	 */
	private final static String VALUES[] = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K",
			"A" };

	/**
	 * Construit le deck du jeu
	 * 
	 * @return Le deck de jeu
	 */
	public ArrayList<Card> buildDeck() {

		final ArrayList<Card> decks = new ArrayList<Card>();

		for (String suit : SUITS) {
			for (String value : VALUES) {
				// String deckValue = value + suit;
				Card card = new Card(suit, value, getCardName(value));
				card.setColor("BLACK");
				if (suit.equals("D") || suit.equals("H")) {
					card.setColor("RED");
				}
				decks.add(card);
			}

		}
		return decks;

	}

	private String getCardName(String value) {
		switch (value) {
		case "T":
			return "10";
		case "J":
			return "Jack";
		case "Q":
			return "Queen";
		case "K":
			return "King";
		case "A":
			return "Ace";
		default:
			return value;
		}
	}

	/**
	 * @param decks
	 */
	public void startPlaying(final ArrayList<Card> decks) {

		PokerGameTurn pokerGameTurn = new PokerGameTurn();

		pokerGameTurn.play(decks);
		
		pokerGameTurn.determinedTurnWinner();
		
		System.out.println(pokerGameTurn.getWinner());

	}
}
