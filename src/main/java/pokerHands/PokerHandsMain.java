package pokerHands;

import java.util.ArrayList;

/**
 * Class permettant de lancer le jeu.
 */
public class PokerHandsMain {

	/**
	 * Main methid 
	 * @param args
	 */
	public static void main(String[] args) {
		
		PokerHandsPrincipal pokerHandsPrincipal = new PokerHandsPrincipal();
		//Creation du deck de jeu
		final ArrayList<Card> decks = pokerHandsPrincipal.buildDeck();
		
		//lancement de la partie partie
		pokerHandsPrincipal.startPlaying(decks);
		
	}

}
